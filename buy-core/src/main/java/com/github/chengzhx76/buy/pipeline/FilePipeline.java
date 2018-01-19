package com.github.chengzhx76.buy.pipeline;

import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePipeline implements Pipeline {

    private final static Logger logger = LoggerFactory.getLogger(FilePipeline.class);


    @Override
    public void process(Request request, Response response) {
    }

}
