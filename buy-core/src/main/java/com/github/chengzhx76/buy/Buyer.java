package com.github.chengzhx76.buy;

import com.github.chengzhx76.buy.httper.Downloader;
import com.github.chengzhx76.buy.httper.HttpClientDownloader;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.pipeline.ConsolePipeline;
import com.github.chengzhx76.buy.pipeline.Pipeline;
import com.github.chengzhx76.buy.processor.Processor;
import com.github.chengzhx76.buy.processor.SimpleProcessor;
import com.github.chengzhx76.buy.utils.CollectionUtils;
import com.github.chengzhx76.buy.utils.ConfigUtils;
import com.github.chengzhx76.buy.utils.OperationType;
import com.github.chengzhx76.buy.utils.StationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public class Buyer {

    protected final static Logger logger = LoggerFactory.getLogger(Buyer.class);

    /** 出发日期不能为空 **/
    private String stationDate;
    /** 车次 **/
    private List<String> stationTrains;
    /** 乘车地点 **/
    private String fromStation;
    /** 到达地点 **/
    private String toStation;
    /** 席别 **/
    private List<String> setType;
    /** 12306帐号 **/
    private String username;
    /** 12306密码 **/
    private String password;

    private Site site;

    private Downloader downloader;

    private Request request;

    private Processor processor;

    private Pipeline pipeline;

    private String fromStationCode;

    private String toStationCode;

    public Buyer() {
    }

    public Buyer(Site site) {
        this.site = site;
    }

    public String getStationDate() {
        return stationDate;
    }

    public Buyer setStationDate(String stationDate) {
        this.stationDate = stationDate;
        return this;
    }

    public List<String> getStationTrains() {
        return stationTrains;
    }

    public Buyer setStationTrains(String stationTrains) {
        if (StringUtils.contains(stationTrains, ",")) {
            this.stationTrains = Arrays.asList(StringUtils.split(stationTrains, ","));
        }
        if (CollectionUtils.isEmpty(getStationTrains())) {
            if (StringUtils.contains(stationTrains, "|")) {
                this.stationTrains = Arrays.asList(StringUtils.split(stationTrains, "|"));
            }
        }
        return this;
    }

    public String getFromStation() {
        return fromStation;
    }

    public Buyer setFromStation(String fromStation) {
        this.fromStation = fromStation;
        return this;
    }

    public String getToStation() {
        return toStation;
    }

    public Buyer setToStation(String toStation) {
        this.toStation = toStation;
        return this;
    }

    public List<String> getSetType() {
        return setType;
    }

    public Buyer setSetType(String setType) {
        if (StringUtils.contains(setType, ",")) {
            this.setType = Arrays.asList(StringUtils.split(setType, ","));
        }
        if (CollectionUtils.isEmpty(getSetType())) {
            if (StringUtils.contains(setType, "|")) {
                this.setType = Arrays.asList(StringUtils.split(setType, "|"));
            }
        }

        return this;
    }

    public String getUsername() {
        return username;
    }

    public Buyer setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Buyer setPassword(String password) {
        this.password = password;
        return this;
    }

    public Site getSite() {
        return site;
    }

    public Buyer setSite(Site site) {
        this.site = site;
        return this;
    }

    public Downloader getDownloader() {
        return downloader;
    }

    public Buyer setDownloader(Downloader downloader) {
        this.downloader = downloader;
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public Buyer setRequest(Request request) {
        this.request = request;
        return this;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Buyer setProcessor(Processor processor) {
        this.processor = processor;
        return this;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public Buyer setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    public String getFromStationCode() {
        return fromStationCode;
    }

    public Buyer setFromStationCode(String fromStationCode) {
        this.fromStationCode = fromStationCode;
        return this;
    }

    public String getToStationCode() {
        return toStationCode;
    }

    public Buyer setToStationCode(String toStationCode) {
        this.toStationCode = toStationCode;
        return this;
    }
    // -------------------------------------------------


    public static Buyer create(Site site) {
        return new Buyer(site);
    }

    private void initComponent() {
        checkConfig();
        if (downloader == null) {
            downloader = new HttpClientDownloader();
        }
        if (request == null) {
            request = new Request();
        }
        request.setOperation(OperationType.CHECK_CAPTCHA);

        if (processor == null) {
            processor = new SimpleProcessor();
        }
        if (pipeline == null) {
            pipeline = new ConsolePipeline();
        }

    }

    public void go() {
        initComponent();
        System.out.println("-----go--->");
        while (!OperationType.END.equals(request.getOperation())) {
            processRequest(request);
        }
    }

    private void processRequest(Request request) {
        preRequest(request);
        Response response = downloader.request(request, site);
        if (response.isRequestSuccess()) {
            onRequestSuccess(request, response);
        } else {
            onRequestFail(response);
        }
    }

    private void preRequest(Request request) {
        processor.preHandle(this, request);
    }

    private void onRequestSuccess(Request request, Response response) {
        if (site.getAcceptStatCode().contains(response.getStatusCode())) {
            processor.afterCompletion(this, request, response);
            pipeline.process(request, response);
        } else {
            logger.warn("page status code error, page {} , code: {}", response.getOperation(), response.getStatusCode());
        }
        sleepTime(request);
    }

    private void sleepTime(Request request) {
        if (request.getSleepTime() > -1L) {
            sleep(request.getSleepTime());
        }else {
            sleep(site.getSleepTime());
        }
    }

    private void onRequestFail(Response response) {
        System.err.println("error");
    }

    private void checkConfig() {
        Properties properties = ConfigUtils.loadProperties("buy.properties");
        if (StringUtils.isBlank(getStationDate())) {
            stationDate = properties.getProperty("stationDate");
            if (StringUtils.isBlank(stationDate)) {
                throw new RuntimeException("出发日期不能为空");
            }
        }
        if (CollectionUtils.isEmpty(getStationTrains())) {
            String stationTrain = properties.getProperty("stationTrains");
            if (StringUtils.contains(stationTrain, ",")) {
                stationTrains = Arrays.asList(StringUtils.split(stationTrain, ","));
            }
            if (CollectionUtils.isEmpty(getStationTrains())) {
                if (StringUtils.contains(stationTrain, "|")) {
                    stationTrains = Arrays.asList(StringUtils.split(stationTrain, "|"));
                }
            }
            if (CollectionUtils.isEmpty(getStationTrains())) {
                throw new RuntimeException("车次不能为空");
            }
        }
        if (StringUtils.isBlank(getFromStation())) {
            fromStation = properties.getProperty("fromStation");
            if (StringUtils.isBlank(fromStation)) {
                throw new RuntimeException("乘车地点不能为空");
            }
        }
        fromStationCode = StationUtils.getStationCode(fromStation);

        if (StringUtils.isBlank(getToStation())) {
            toStation = properties.getProperty("toStation");
            if (StringUtils.isBlank(toStation)) {
                throw new RuntimeException("到达站不能为空");
            }
        }
        toStationCode = StationUtils.getStationCode(toStation);

        if (CollectionUtils.isEmpty(getSetType())) {
            String type = properties.getProperty("setType");

            if (StringUtils.contains(type, ",")) {
                setType = Arrays.asList(StringUtils.split(type, ","));
            }
            if (CollectionUtils.isEmpty(getSetType())) {
                if (StringUtils.contains(type, "|")) {
                    setType = Arrays.asList(StringUtils.split(type, "|"));
                }
            }
            if (CollectionUtils.isEmpty(getSetType())) {
                throw new RuntimeException("席别不能为空");
            }
        }
        if (StringUtils.isBlank(getUsername())) {
            username = properties.getProperty("username");
            if (StringUtils.isBlank(username)) {
                throw new RuntimeException("用户名不能为空");
            }
        }
        if (StringUtils.isBlank(getPassword())) {
            password = properties.getProperty("password");
            if (StringUtils.isBlank(password)) {
                throw new RuntimeException("密码不能为空");
            }
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted when sleep",e);
        }
    }

}
