(function () {
    function Pa() {
        if (-1 == F("RAIL_EXPIRATION")) for (var a = 0; 10 > a; a++) G(function () {
            (new ja).getFingerPrint()
        }, 20 + 2E3 * Math.pow(a, 2)); else (new ja).getFingerPrint();
        G(function () {
            r.setInterval(function () {
                (new ja).getFingerPrint()
            }, 3E5)
        }, 3E5)
    }

    function nb(a) {
        this.isTimeout = 0;
        var b = this, c = r.RTCPeerConnection || r.webkitRTCPeerConnection || r.mozRTCPeerConnection;
        if ("function" == typeof c) {
            try {
                var d = new c({iceServers: []});
                d.createDataChannel("", {reliable: !1})
            } catch (f) {
                if (2 != b.isTimeout) {
                    b.isTimeout = 1;
                    a();
                    return
                }
            }
            var e = !1;
            d.onicecandidate = function (c) {
                var d = /([0-9]{1,3}(\.[0-9]{1,3}){3})/, f = [];
                "complete" != c.target.iceGatheringState || e || (e = !0, c.target.localDescription.sdp.split("\n").forEach(function (a) {
                    (a = d.exec(a)) && "127.0.0.1" != a[1] && "0.0.0.0" != a[1] && -1 === f.indexOf(a[1]) && f.push(a[1])
                }), 2 != b.isTimeout && (b.isTimeout = 1, a({localAddr: 0 < f.length ? f.sort()[0] : ""})))
            };
            d.onaddstream = function (a) {
                remoteVideo.src = r.URL.createObjectURL(a.stream)
            };
            d.createOffer(function (a) {
                d.setLocalDescription(a, function () {
                    },
                    function () {
                    })
            }, function () {
            }, {})
        } else a();
        G(function () {
            0 == b.isTimeout && (b.isTimeout = 2, a())
        }, 500)
    }

    function Qa(a) {
        for (var b = "", c = a.length, d = 0; d < c; d++) var e = a.charAt(d).charCodeAt(0), b = 127 === e ? b + String.fromCharCode(0) : b + String.fromCharCode(e + 1);
        return b
    }

    function Ra(a) {
        return null != /[\\\"<>\.;]/.exec(a) && "undefined" != typeof encodeURIComponent ? encodeURIComponent(a) : a
    }

    function P(a, b) {
        if (Sa) {
            var c = b ? "visible" : "hidden";
            Q && J(a) ? J(a).style.visibility = c : Ta("#" + a, "visibility:" + c)
        }
    }

    function Ta(a, b, c, d) {
        if (!g.ie ||
            !g.mac) {
            var e = q.getElementsByTagName("head")[0];
            e && (c = c && "string" == typeof c ? c : "screen", d && (ya = K = null), K && ya == c || (d = q.createElement("style"), d.setAttribute("type", "text/css"), d.setAttribute("media", c), K = e.appendChild(d), g.ie && g.win && "undefined" != typeof q.styleSheets && 0 < q.styleSheets.length && (K = q.styleSheets[q.styleSheets.length - 1]), ya = c), g.ie && g.win ? K && "object" == typeof K.addRule && K.addRule(a, b) : K && "undefined" != typeof q.createTextNode && K.appendChild(q.createTextNode(a + " {" + b + "}")))
        }
    }

    function ka(a) {
        var b =
            g.pv;
        a = a.split(".");
        a[0] = parseInt(a[0], 10);
        a[1] = parseInt(a[1], 10) || 0;
        a[2] = parseInt(a[2], 10) || 0;
        return b[0] > a[0] || b[0] == a[0] && b[1] > a[1] || b[0] == a[0] && b[1] == a[1] && b[2] >= a[2] ? !0 : !1
    }

    function J(a) {
        var b = null;
        try {
            b = q.getElementById(a)
        } catch (c) {
        }
        return b
    }

    function Ua(a) {
        var b = J(a);
        b && "OBJECT" == b.nodeName && (g.ie && g.win ? (b.style.display = "none", function d() {
            if (4 == b.readyState) {
                var e = J(a);
                if (e) {
                    for (var f in e) "function" == typeof e[f] && (e[f] = null);
                    e.parentNode.removeChild(e)
                }
            } else G(d, 10)
        }()) : b.parentNode.removeChild(b))
    }

    function za(a, b, c) {
        var d, e = J(c);
        if (g.wk && 312 > g.wk) return d;
        if (e) if ("undefined" == typeof a.id && (a.id = c), g.ie && g.win) {
            var f = "", h;
            for (h in a) a[h] != Object.prototype[h] && ("data" == h.toLowerCase() ? b.movie = a[h] : "styleclass" == h.toLowerCase() ? f += ' class\x3d"' + a[h] + '"' : "classid" != h.toLowerCase() && (f += " " + h + '\x3d"' + a[h] + '"'));
            h = "";
            for (var m in b) b[m] != Object.prototype[m] && (h += '\x3cparam name\x3d"' + m + '" value\x3d"' + b[m] + '" /\x3e');
            e.outerHTML = '\x3cobject classid\x3d"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' +
                f + "\x3e" + h + "\x3c/object\x3e";
            la[la.length] = a.id;
            d = J(a.id)
        } else {
            m = q.createElement("object");
            m.setAttribute("type", "application/x-shockwave-flash");
            for (var k in a) a[k] != Object.prototype[k] && ("styleclass" == k.toLowerCase() ? m.setAttribute("class", a[k]) : "classid" != k.toLowerCase() && m.setAttribute(k, a[k]));
            for (f in b) b[f] != Object.prototype[f] && "movie" != f.toLowerCase() && (a = m, h = f, k = b[f], c = q.createElement("param"), c.setAttribute("name", h), c.setAttribute("value", k), a.appendChild(c));
            e.parentNode.replaceChild(m,
                e);
            d = m
        }
        return d
    }

    function Aa(a) {
        var b = q.createElement("div");
        if (g.win && g.ie) b.innerHTML = a.innerHTML; else if (a = a.getElementsByTagName("object")[0]) if (a = a.childNodes) for (var c = a.length, d = 0; d < c; d++) 1 == a[d].nodeType && "PARAM" == a[d].nodeName || 8 == a[d].nodeType || b.appendChild(a[d].cloneNode(!0));
        return b
    }

    function ob(a) {
        if (g.ie && g.win && 4 != a.readyState) {
            var b = q.createElement("div");
            a.parentNode.insertBefore(b, a);
            b.parentNode.replaceChild(Aa(a), b);
            a.style.display = "none";
            (function d() {
                4 == a.readyState ? a.parentNode.removeChild(a) :
                    G(d, 10)
            })()
        } else a.parentNode.replaceChild(Aa(a), a)
    }

    function Ba(a, b, c, d) {
        ma = !0;
        Ca = d || null;
        Va = {id: c, success: !1};
        var e = J(c);
        if (e) {
            "OBJECT" == e.nodeName ? (Z = Aa(e), na = null) : (Z = e, na = c);
            a.id = "SWFObjectExprInst";
            if ("undefined" == typeof a.width || !/%$/.test(a.width) && 310 > parseInt(a.width, 10)) a.width = "310";
            if ("undefined" == typeof a.height || !/%$/.test(a.height) && 137 > parseInt(a.height, 10)) a.height = "137";
            q.title = q.title.slice(0, 47) + " - Flash Player Installation";
            d = g.ie && g.win ? "ActiveX" : "PlugIn";
            d = "MMredirectURL\x3d" +
                I.location.toString().replace(/&/g, "%26") + "\x26MMplayerType\x3d" + d + "\x26MMdoctitle\x3d" + q.title;
            b.flashvars = "undefined" != typeof b.flashvars ? b.flashvars + ("\x26" + d) : d;
            g.ie && g.win && 4 != e.readyState && (d = q.createElement("div"), c += "SWFObjectNew", d.setAttribute("id", c), e.parentNode.insertBefore(d, e), e.style.display = "none", function h() {
                4 == e.readyState ? e.parentNode.removeChild(e) : G(h, 10)
            }());
            za(a, b, c)
        }
    }

    function Da() {
        return !ma && ka("6.0.65") && (g.win || g.mac) && !(g.wk && 312 > g.wk)
    }

    function Ea(a) {
        var b = null;
        (a = J(a)) &&
        "OBJECT" == a.nodeName && ("undefined" != typeof a.SetVariable ? b = a : (a = a.getElementsByTagName("object")[0]) && (b = a));
        return b
    }

    function Fa() {
        var a = L.length;
        if (0 < a) for (var b = 0; b < a; b++) {
            var c = L[b].id, d = L[b].callbackFn, e = {success: !1, id: c};
            if (0 < g.pv[0]) {
                var f = J(c);
                if (f) if (!ka(L[b].swfVersion) || g.wk && 312 > g.wk) if (L[b].expressInstall && Da()) {
                    e = {};
                    e.data = L[b].expressInstall;
                    e.width = f.getAttribute("width") || "0";
                    e.height = f.getAttribute("height") || "0";
                    f.getAttribute("class") && (e.styleclass = f.getAttribute("class"));
                    f.getAttribute("align") &&
                    (e.align = f.getAttribute("align"));
                    for (var h = {}, f = f.getElementsByTagName("param"), m = f.length, k = 0; k < m; k++) "movie" != f[k].getAttribute("name").toLowerCase() && (h[f[k].getAttribute("name")] = f[k].getAttribute("value"));
                    Ba(e, h, c, d)
                } else ob(f), d && d(e); else P(c, !0), d && (e.success = !0, e.ref = Ea(c), d(e))
            } else P(c, !0), d && ((c = Ea(c)) && "undefined" != typeof c.SetVariable && (e.success = !0, e.ref = c), d(e))
        }
    }

    function Wa(a) {
        if ("undefined" != typeof I.addEventListener) I.addEventListener("load", a, !1); else if ("undefined" != typeof q.addEventListener) q.addEventListener("load",
            a, !1); else if ("undefined" != typeof I.attachEvent) {
            var b = I;
            b.attachEvent("onload", a);
            U[U.length] = [b, "onload", a]
        } else if ("function" == typeof I.onload) {
            var c = I.onload;
            I.onload = function () {
                c();
                a()
            }
        } else I.onload = a
    }

    function Xa(a) {
        Q ? a() : oa[oa.length] = a
    }

    function V() {
        if (!Q) {
            try {
                var a = q.getElementsByTagName("body")[0].appendChild(q.createElement("span"));
                a.parentNode.removeChild(a)
            } catch (c) {
                return
            }
            Q = !0;
            for (var a = oa.length, b = 0; b < a; b++) oa[b]()
        }
    }

    function Ga(a) {
        if (!a) return "";
        if (pb(a)) return a.replace(/\s/g, "");
        -1 != a.indexOf("://") && (a = a.substr(a.indexOf("://") + 3));
        var b = "com net org gov edu mil biz name info mobi pro travel museum int areo post rec".split(" "),
            c = a.split(".");
        if (1 >= c.length || !isNaN(c[c.length - 1])) return a;
        for (a = 0; a < b.length && b[a] != c[c.length - 1];) a++;
        if (a != b.length) return "." + c[c.length - 2] + "." + c[c.length - 1];
        for (a = 0; a < b.length && b[a] != c[c.length - 2];) a++;
        return a == b.length ? c[c.length - 2] + "." + c[c.length - 1] : "." + c[c.length - 3] + "." + c[c.length - 2] + "." + c[c.length - 1]
    }

    function qb(a) {
        var b = a.split(".");
        if (4 !== b.length) throw Error("Invalid format -- expecting a.b.c.d");
        for (var c = a = 0; c < b.length; ++c) {
            var d = parseInt(b[c], 10);
            if (Number.isNaN(d) || 0 > d || 255 < d) throw Error("Each octet must be between 0 and 255");
            a |= d << 8 * (b.length - c - 1);
            a >>>= 0
        }
        return a
    }

    function Ya(a) {
        return 4294967296 * (a - (a | 0)) | 0
    }

    function aa(a) {
        if (!(this instanceof aa)) return new aa(a);
        this.options = this.extend(a, {
            swfPath: "flash/compiled/FontList.swf",
            swfContainerId: "fingerprintjs2",
            userDefinedFonts: [],
            detectScreenOrientation: !0,
            sortPluginsFor: [/palemoon/i]
        });
        this.nativeForEach = Array.prototype.forEach;
        this.nativeMap = Array.prototype.map
    }

    function z(a, b, c, d, e, f) {
        a = M(M(b, a), M(d, f));
        return M(a << e | a >>> 32 - e, c)
    }

    function B(a, b, c, d, e, f, h) {
        return z(b & c | ~b & d, a, b, e, f, h)
    }

    function C(a, b, c, d, e, f, h) {
        return z(b & d | c & ~d, a, b, e, f, h)
    }

    function M(a, b) {
        var c = (a & 65535) + (b & 65535);
        return (a >> 16) + (b >> 16) + (c >> 16) << 16 | c & 65535
    }

    function D(a, b, c, d, e, f, h) {
        return z(c ^ (b | ~d), a, b, e, f, h)
    }

    function ba(a) {
        for (var b = [], c = (1 << ca) - 1, d = 0; d < a.length * ca; d += ca) b[d >> 5] |= (a.charCodeAt(d / ca) & c) << d % 32;
        a = a.length *
            ca;
        b[a >> 5] |= 128 << a % 32;
        b[(a + 64 >>> 9 << 4) + 14] = a;
        a = 1732584193;
        for (var c = -271733879, d = -1732584194, e = 271733878, f = 0; f < b.length; f += 16) {
            var h = a, m = c, k = d, g = e;
            a = B(a, c, d, e, b[f + 0], 7, -680876936);
            e = B(e, a, c, d, b[f + 1], 12, -389564586);
            d = B(d, e, a, c, b[f + 2], 17, 606105819);
            c = B(c, d, e, a, b[f + 3], 22, -1044525330);
            a = B(a, c, d, e, b[f + 4], 7, -176418897);
            e = B(e, a, c, d, b[f + 5], 12, 1200080426);
            d = B(d, e, a, c, b[f + 6], 17, -1473231341);
            c = B(c, d, e, a, b[f + 7], 22, -45705983);
            a = B(a, c, d, e, b[f + 8], 7, 1770035416);
            e = B(e, a, c, d, b[f + 9], 12, -1958414417);
            d = B(d, e, a, c, b[f + 10],
                17, -42063);
            c = B(c, d, e, a, b[f + 11], 22, -1990404162);
            a = B(a, c, d, e, b[f + 12], 7, 1804603682);
            e = B(e, a, c, d, b[f + 13], 12, -40341101);
            d = B(d, e, a, c, b[f + 14], 17, -1502002290);
            c = B(c, d, e, a, b[f + 15], 22, 1236535329);
            a = C(a, c, d, e, b[f + 1], 5, -165796510);
            e = C(e, a, c, d, b[f + 6], 9, -1069501632);
            d = C(d, e, a, c, b[f + 11], 14, 643717713);
            c = C(c, d, e, a, b[f + 0], 20, -373897302);
            a = C(a, c, d, e, b[f + 5], 5, -701558691);
            e = C(e, a, c, d, b[f + 10], 9, 38016083);
            d = C(d, e, a, c, b[f + 15], 14, -660478335);
            c = C(c, d, e, a, b[f + 4], 20, -405537848);
            a = C(a, c, d, e, b[f + 9], 5, 568446438);
            e = C(e, a, c, d, b[f +
            14], 9, -1019803690);
            d = C(d, e, a, c, b[f + 3], 14, -187363961);
            c = C(c, d, e, a, b[f + 8], 20, 1163531501);
            a = C(a, c, d, e, b[f + 13], 5, -1444681467);
            e = C(e, a, c, d, b[f + 2], 9, -51403784);
            d = C(d, e, a, c, b[f + 7], 14, 1735328473);
            c = C(c, d, e, a, b[f + 12], 20, -1926607734);
            a = z(c ^ d ^ e, a, c, b[f + 5], 4, -378558);
            e = z(a ^ c ^ d, e, a, b[f + 8], 11, -2022574463);
            d = z(e ^ a ^ c, d, e, b[f + 11], 16, 1839030562);
            c = z(d ^ e ^ a, c, d, b[f + 14], 23, -35309556);
            a = z(c ^ d ^ e, a, c, b[f + 1], 4, -1530992060);
            e = z(a ^ c ^ d, e, a, b[f + 4], 11, 1272893353);
            d = z(e ^ a ^ c, d, e, b[f + 7], 16, -155497632);
            c = z(d ^ e ^ a, c, d, b[f + 10], 23, -1094730640);
            a = z(c ^ d ^ e, a, c, b[f + 13], 4, 681279174);
            e = z(a ^ c ^ d, e, a, b[f + 0], 11, -358537222);
            d = z(e ^ a ^ c, d, e, b[f + 3], 16, -722521979);
            c = z(d ^ e ^ a, c, d, b[f + 6], 23, 76029189);
            a = z(c ^ d ^ e, a, c, b[f + 9], 4, -640364487);
            e = z(a ^ c ^ d, e, a, b[f + 12], 11, -421815835);
            d = z(e ^ a ^ c, d, e, b[f + 15], 16, 530742520);
            c = z(d ^ e ^ a, c, d, b[f + 2], 23, -995338651);
            a = D(a, c, d, e, b[f + 0], 6, -198630844);
            e = D(e, a, c, d, b[f + 7], 10, 1126891415);
            d = D(d, e, a, c, b[f + 14], 15, -1416354905);
            c = D(c, d, e, a, b[f + 5], 21, -57434055);
            a = D(a, c, d, e, b[f + 12], 6, 1700485571);
            e = D(e, a, c, d, b[f + 3], 10, -1894986606);
            d = D(d, e, a, c,
                b[f + 10], 15, -1051523);
            c = D(c, d, e, a, b[f + 1], 21, -2054922799);
            a = D(a, c, d, e, b[f + 8], 6, 1873313359);
            e = D(e, a, c, d, b[f + 15], 10, -30611744);
            d = D(d, e, a, c, b[f + 6], 15, -1560198380);
            c = D(c, d, e, a, b[f + 13], 21, 1309151649);
            a = D(a, c, d, e, b[f + 4], 6, -145523070);
            e = D(e, a, c, d, b[f + 11], 10, -1120210379);
            d = D(d, e, a, c, b[f + 2], 15, 718787259);
            c = D(c, d, e, a, b[f + 9], 21, -343485551);
            a = M(a, h);
            c = M(c, m);
            d = M(d, k);
            e = M(e, g)
        }
        b = [a, c, d, e];
        a = rb ? "0123456789ABCDEF" : "0123456789abcdef";
        c = "";
        for (d = 0; d < 4 * b.length; d++) c += a.charAt(b[d >> 2] >> d % 4 * 8 + 4 & 15) + a.charAt(b[d >> 2] >>
            d % 4 * 8 & 15);
        return c
    }

    function F(a) {
        var b, c, d, e = v.cookie.split(";");
        for (b = 0; b < e.length; b++) if (c = e[b].substr(0, e[b].indexOf("\x3d")), d = e[b].substr(e[b].indexOf("\x3d") + 1), c = c.replace(/^\s+|\s+$/g, ""), a = a.replace(/^\s+|\s+$/g, ""), c == a) return unescape(d)
    }

    function W(a, b, c, d, e, f) {
        var h = new Date;
        h.setTime(h.getTime());
        -1 != c ? (c *= 864E5, h = new Date(h.getTime() + c), cookieString = a + "\x3d" + escape(b) + (c ? ";expires\x3d" + h.toGMTString() : "") + (d ? ";path\x3d" + d : "") + (e ? ";domain\x3d" + e : "") + (f ? ";secure" : "")) : (h = -1, cookieString =
            a + "\x3d" + escape(b) + (c ? ";expires\x3d" + h : "") + (d ? ";path\x3d" + d : "") + (e ? ";domain\x3d" + e : "") + (f ? ";secure" : ""));
        v.cookie = cookieString
    }

    function pb(a) {
        a = a.replace(/\s/g, "");
        if (/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(a)) {
            a = a.split(".");
            if (0 == parseInt(parseFloat(a[0])) || 0 == parseInt(parseFloat(a[3]))) return !1;
            for (var b = 0; b < a.length; b++) if (255 < parseInt(parseFloat(a[b]))) return !1;
            return !0
        }
        return !1
    }

    function n(a, b) {
        this.key = a;
        this.value = b
    }

    function W(a, b, c) {
        var d = new Date;
        d.setTime(d.getTime() + 864E5 * Number(c));
        v.cookie = a + "\x3d" + b + "; path\x3d/;expires \x3d " + d.toGMTString() + ";domain\x3d" + Ga(r.location.host.split(":")[0])
    }

    function Ha() {
        var a = k.userAgent.toLowerCase();
        return 0 <= a.indexOf("windows phone") ? "WindowsPhone" : 0 <= a.indexOf("win") ? "Windows" : 0 <= a.indexOf("android") ? "Android" : 0 <= a.indexOf("linux") ? "Linux" : 0 <= a.indexOf("iphone") || 0 <= a.indexOf("ipad") ? "iOS" : 0 <= a.indexOf("mac") ? "Mac" : "Other"
    }

    function ja() {
        this.ec = new evercookie;
        this.deviceEc = new evercookie;
        this.cfp = new aa;
        this.packageString = "";
        this.moreInfoArray =
            []
    }

    var v = document, r = window, k = navigator, x = screen, G = setTimeout, sb = top, tb = location, ub = parent,
        Za = ["WEB", "WAP"], $a = {
            removeElem: function (a) {
                var b = a.parentNode;
                if (b) try {
                    b.removeChild(a)
                } catch (c) {
                }
            }, parseData: function (a) {
                var b = "";
                if ("string" === typeof a) b = a; else if ("object" === typeof a) for (var c in a) b += "\x26" + c + "\x3d" + encodeURIComponent(a[c]);
                b += "\x26_time\x3d" + this.now();
                return b = b.substr(1)
            }, rand: function () {
                return Math.random().toString().substr(2)
            }, getJSON: function (a, b, c) {
                b = v.createElement("script");
                b.type =
                    "text/javascript";
                b.src = a;
                b.id = "id_callbackFunction";
                r.callbackFunction = function (a) {
                    r.callbackFunction = void 0;
                    var b = v.getElementById("id_callbackFunction");
                    b && $a.removeElem(b);
                    c(a)
                };
                (a = v.getElementsByTagName("head")) && a[0] && a[0].appendChild(b)
            }, now: function () {
                return (new Date).getTime()
            }
        };
    "object" != typeof JSON && (JSON = {});
    (function () {
        function a(a) {
            return 10 > a ? "0" + a : a
        }

        function b() {
            return this.valueOf()
        }

        function c(a) {
            return p.lastIndex = 0, p.test(a) ? '"' + a.replace(p, function (a) {
                var b = h[a];
                return "string" ==
                typeof b ? b : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
            }) + '"' : '"' + a + '"'
        }

        function d(a, b) {
            var u, h, y, E, l, k = e, p = b[a];
            switch (p && "object" == typeof p && "function" == typeof p.toJSON && (p = p.toJSON(a)), "function" == typeof m && (p = m.call(b, a, p)), typeof p) {
                case "string":
                    return c(p);
                case "number":
                    return isFinite(p) ? String(p) : "null";
                case "boolean":
                case "null":
                    return String(p);
                case "object":
                    if (!p) return "null";
                    if (e += f, l = [], "[object Array]" === Object.prototype.toString.apply(p)) {
                        E = p.length;
                        for (u = 0; E > u; u += 1) l[u] =
                            d(u, p) || "null";
                        return y = 0 === l.length ? "[]" : e ? "[\n" + e + l.join(",\n" + e) + "\n" + k + "]" : "[" + l.join(",") + "]", e = k, y
                    }
                    if (m && "object" == typeof m) for (E = m.length, u = 0; E > u; u += 1) "string" == typeof m[u] && (h = m[u], y = d(h, p), y && l.push(c(h) + (e ? ": " : ":") + y)); else for (h in p) Object.prototype.hasOwnProperty.call(p, h) && (y = d(h, p), y && l.push(c(h) + (e ? ": " : ":") + y));
                    return y = 0 === l.length ? "{}" : e ? "{\n" + e + l.join(",\n" + e) + "\n" + k + "}" : "{" + l.join(",") + "}", e = k, y
            }
        }

        var e, f, h, m, k = /^[\],:{}\s]*$/, g = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,
            N = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
            l = /(?:^|:|,)(?:\s*\[)+/g,
            p = /[\\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
            n = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
        "function" != typeof Date.prototype.toJSON && (Date.prototype.toJSON = function () {
            return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + a(this.getUTCMonth() + 1) + "-" + a(this.getUTCDate()) + "T" + a(this.getUTCHours()) + ":" + a(this.getUTCMinutes()) +
                ":" + a(this.getUTCSeconds()) + "Z" : null
        }, Boolean.prototype.toJSON = b, Number.prototype.toJSON = b, String.prototype.toJSON = b);
        "function" != typeof JSON.stringify && (h = {
            "\\": "\\\\",
            "   ": "\\t",
            "\b": "\\b",
            "\r": "\\r",
            "\n": "\\n",
            "\f": "\\f",
            '"': '\\"'
        }, JSON.stringify = function (a, b, c) {
            var u;
            if (e = "", f = "", "number" == typeof c) for (u = 0; c > u; u += 1) f += " "; else "string" == typeof c && (f = c);
            if (m = b, b && "function" != typeof b && ("object" != typeof b || "number" != typeof b.length)) throw Error("JSON.stringify");
            return d("", {"": a})
        });
        "function" !=
        typeof JSON.parse && (JSON.parse = function (a, b) {
            function c(a, d) {
                var e, f, u = a[d];
                if (u && "object" == typeof u) for (e in u) Object.prototype.hasOwnProperty.call(u, e) && (f = c(u, e), void 0 !== f ? u[e] = f : delete u[e]);
                return b.call(a, d, u)
            }

            var d;
            if (a = String(a), n.lastIndex = 0, n.test(a) && (a = a.replace(n, function (a) {
                    return "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
                })), k.test(a.replace(g, "@").replace(N, "]").replace(l, ""))) return d = eval("(" + a + ")"), "function" == typeof b ? c({"": d}, "") : d;
            throw new SyntaxError("JSON.parse");
        })
    })();
    debug = !1;
    Array.prototype.indexOf || (Array.prototype.indexOf = function (a, b) {
        var c;
        if (null == this) throw new TypeError("'this' is null or undefined");
        var d = Object(this), e = d.length >>> 0;
        if (0 === e) return -1;
        c = +b || 0;
        Infinity === Math.abs(c) && (c = 0);
        if (c >= e) return -1;
        for (c = Math.max(0 <= c ? c : e - Math.abs(c), 0); c < e;) {
            if (c in d && d[c] === a) return c;
            c++
        }
        return -1
    });
    var Ia;
    if (!(Ia = R)) {
        var da = Math, pa = {}, qa = pa.lib = {}, ab = function () {
        }, ea = qa.Base = {
            init: function () {
            }, extend: function (a) {
                ab.prototype = this;
                var b = new ab;
                a && b.mixIn(a);
                b.hasOwnProperty("init") || (b.init = function () {
                    b.$super.init.apply(this, arguments)
                });
                b.init.prototype = b;
                b.$super = this;
                return b
            }, clone: function () {
                return this.init.prototype.extend(this)
            }, mixIn: function (a) {
                for (var b in a) a.hasOwnProperty(b) && (this[b] = a[b]);
                a.hasOwnProperty("toString") && (this.toString = a.toString)
            }, create: function () {
                var a = this.extend();
                a.init.apply(a, arguments);
                return a
            }
        }, fa = qa.WordArray = ea.extend({
            concat: function (a) {
                var b = this.words, c = a.words, d = this.sigBytes;
                a = a.sigBytes;
                this.clamp();
                if (d % 4) for (var e = 0; e < a; e++) b[d + e >>> 2] |= (c[e >>> 2] >>> 24 - e % 4 * 8 & 255) << 24 - (d + e) % 4 * 8; else if (65535 < c.length) for (e = 0; e < a; e += 4) b[d + e >>> 2] = c[e >>> 2]; else b.push.apply(b, c);
                this.sigBytes += a;
                return this
            }, toString: function (a) {
                return (a || vb).stringify(this)
            }, init: function (a, b) {
                a = this.words = a || [];
                this.sigBytes = void 0 != b ? b : 4 * a.length
            }, clone: function () {
                var a = ea.clone.call(this);
                a.words = this.words.slice(0);
                return a
            }, random: function (a) {
                for (var b = [], c = 0; c < a; c += 4) b.push(4294967296 * da.random() | 0);
                return new fa.init(b, a)
            },
            clamp: function () {
                var a = this.words, b = this.sigBytes;
                a[b >>> 2] &= 4294967295 << 32 - b % 4 * 8;
                a.length = da.ceil(b / 4)
            }
        }), Ja = pa.enc = {}, vb = Ja.Hex = {
            parse: function (a) {
                for (var b = a.length, c = [], d = 0; d < b; d += 2) c[d >>> 3] |= parseInt(a.substr(d, 2), 16) << 24 - d % 8 * 4;
                return new fa.init(c, b / 2)
            }, stringify: function (a) {
                var b = a.words;
                a = a.sigBytes;
                for (var c = [], d = 0; d < a; d++) {
                    var e = b[d >>> 2] >>> 24 - d % 4 * 8 & 255;
                    c.push((e >>> 4).toString(16));
                    c.push((e & 15).toString(16))
                }
                return c.join("")
            }
        }, bb = Ja.Latin1 = {
            parse: function (a) {
                for (var b = a.length, c = [], d = 0; d <
                b; d++) c[d >>> 2] |= (a.charCodeAt(d) & 255) << 24 - d % 4 * 8;
                return new fa.init(c, b)
            }, stringify: function (a) {
                var b = a.words;
                a = a.sigBytes;
                for (var c = [], d = 0; d < a; d++) c.push(String.fromCharCode(b[d >>> 2] >>> 24 - d % 4 * 8 & 255));
                return c.join("")
            }
        }, wb = Ja.Utf8 = {
            stringify: function (a) {
                try {
                    return decodeURIComponent(escape(bb.stringify(a)))
                } catch (b) {
                    throw Error("Malformed UTF-8 data");
                }
            }, parse: function (a) {
                return bb.parse(unescape(encodeURIComponent(a)))
            }
        }, cb = qa.BufferedBlockAlgorithm = ea.extend({
            clone: function () {
                var a = ea.clone.call(this);
                a._data = this._data.clone();
                return a
            }, reset: function () {
                this._data = new fa.init;
                this._nDataBytes = 0
            }, _process: function (a) {
                var b = this._data, c = b.words, d = b.sigBytes, e = this.blockSize, f = d / (4 * e),
                    f = a ? da.ceil(f) : da.max((f | 0) - this._minBufferSize, 0);
                a = f * e;
                d = da.min(4 * a, d);
                if (a) {
                    for (var h = 0; h < a; h += e) this._doProcessBlock(c, h);
                    h = c.splice(0, a);
                    b.sigBytes -= d
                }
                return new fa.init(h, d)
            }, _minBufferSize: 0, _append: function (a) {
                "string" == typeof a && (a = wb.parse(a));
                this._data.concat(a);
                this._nDataBytes += a.sigBytes
            }
        });
        qa.Hasher =
            cb.extend({
                blockSize: 16, update: function (a) {
                    this._append(a);
                    this._process();
                    return this
                }, cfg: ea.extend(), _createHelper: function (a) {
                    return function (b, c) {
                        return (new a.init(c)).finalize(b)
                    }
                }, init: function (a) {
                    this.cfg = this.cfg.extend(a);
                    this.reset()
                }, reset: function () {
                    cb.reset.call(this);
                    this._doReset()
                }, finalize: function (a) {
                    a && this._append(a);
                    return this._doFinalize()
                }, _createHmacHelper: function (a) {
                    return function (b, c) {
                        return (new xb.HMAC.init(a, c)).finalize(b)
                    }
                }
            });
        var xb = pa.algo = {};
        Ia = pa
    }
    for (var R = Ia,
             ra = Math, sa = R, S = sa.lib, yb = S.WordArray, ta = S.Hasher, S = sa.algo, db = [], eb = [], ua = 2, ga = 0; 64 > ga;) {
        var X;
        a:{
            X = ua;
            for (var zb = ra.sqrt(X), Ka = 2; Ka <= zb; Ka++) if (!(X % Ka)) {
                X = !1;
                break a
            }
            X = !0
        }
        X && (8 > ga && (db[ga] = Ya(ra.pow(ua, .5))), eb[ga] = Ya(ra.pow(ua, 1 / 3)), ga++);
        ua++
    }
    var T = [], S = S.SHA256 = ta.extend({
        _doFinalize: function () {
            var a = this._data, b = a.words, c = 8 * this._nDataBytes, d = 8 * a.sigBytes;
            b[d >>> 5] |= 128 << 24 - d % 32;
            b[(d + 64 >>> 9 << 4) + 14] = ra.floor(c / 4294967296);
            b[(d + 64 >>> 9 << 4) + 15] = c;
            a.sigBytes = 4 * b.length;
            this._process();
            return this._hash
        },
        clone: function () {
            var a = ta.clone.call(this);
            a._hash = this._hash.clone();
            return a
        }, _doReset: function () {
            this._hash = new yb.init(db.slice(0))
        }, _doProcessBlock: function (a, b) {
            for (var c = this._hash.words, d = c[0], e = c[1], f = c[2], h = c[3], m = c[4], k = c[5], g = c[6], N = c[7], l = 0; 64 > l; l++) {
                if (16 > l) T[l] = a[b + l] | 0; else {
                    var p = T[l - 15], n = T[l - 2];
                    T[l] = ((p << 25 | p >>> 7) ^ (p << 14 | p >>> 18) ^ p >>> 3) + T[l - 7] + ((n << 15 | n >>> 17) ^ (n << 13 | n >>> 19) ^ n >>> 10) + T[l - 16]
                }
                p = N + ((m << 26 | m >>> 6) ^ (m << 21 | m >>> 11) ^ (m << 7 | m >>> 25)) + (m & k ^ ~m & g) + eb[l] + T[l];
                n = ((d << 30 | d >>> 2) ^ (d <<
                    19 | d >>> 13) ^ (d << 10 | d >>> 22)) + (d & e ^ d & f ^ e & f);
                N = g;
                g = k;
                k = m;
                m = h + p | 0;
                h = f;
                f = e;
                e = d;
                d = p + n | 0
            }
            c[0] = c[0] + d | 0;
            c[1] = c[1] + e | 0;
            c[2] = c[2] + f | 0;
            c[3] = c[3] + h | 0;
            c[4] = c[4] + m | 0;
            c[5] = c[5] + k | 0;
            c[6] = c[6] + g | 0;
            c[7] = c[7] + N | 0
        }
    });
    sa.SHA256 = ta._createHelper(S);
    sa.HmacSHA256 = ta._createHmacHelper(S);
    var fb = R, Ab = fb.lib.WordArray;
    fb.enc.Base64 = {
        stringify: function (a) {
            var b = a.words, c = a.sigBytes, d = this._map;
            a.clamp();
            a = [];
            for (var e = 0; e < c; e += 3) for (var f = (b[e >>> 2] >>> 24 - e % 4 * 8 & 255) << 16 | (b[e + 1 >>> 2] >>> 24 - (e + 1) % 4 * 8 & 255) << 8 | b[e + 2 >>> 2] >>> 24 - (e + 2) %
                4 * 8 & 255, h = 0; 4 > h && e + .75 * h < c; h++) a.push(d.charAt(f >>> 6 * (3 - h) & 63));
            if (b = d.charAt(64)) for (; a.length % 4;) a.push(b);
            return a.join("")
        }, _map: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", parse: function (a) {
            var b = a.length, c = this._map, d = c.charAt(64);
            d && (d = a.indexOf(d), -1 != d && (b = d));
            for (var d = [], e = 0, f = 0; f < b; f++) if (f % 4) {
                var h = c.indexOf(a.charAt(f - 1)) << f % 4 * 2, m = c.indexOf(a.charAt(f)) >>> 6 - f % 4 * 2;
                d[e >>> 2] |= (h | m) << 24 - e % 4 * 8;
                e++
            }
            return Ab.create(d, e)
        }
    };
    var Bb = ["scrDeviceXDPI", "scrColorDepth", "scrWidth",
            "scrHeight"], ca = 8,
        Cb = "appCodeName appMinorVersion appName cpuClass onLine systemLanguage userLanguage historyList hasLiedLanguages hasLiedResolution hasLiedOs hasLiedBrowser".split(" ");
    Array.prototype.indexOf || (Array.prototype.indexOf = function (a, b) {
        var c;
        if (null == this) throw new TypeError('"this" is null or not defined');
        var d = Object(this), e = d.length >>> 0;
        if (0 === e) return -1;
        c = +b || 0;
        Infinity === Math.abs(c) && (c = 0);
        if (c >= e) return -1;
        for (c = Math.max(0 <= c ? c : e - Math.abs(c), 0); c < e;) {
            if (c in d && d[c] === a) return c;
            c++
        }
        return -1
    });
    aa.prototype = {
        languageKey: function (a) {
            this.options.excludeLanguage || a.push({
                value: k.language || k.userLanguage || k.browserLanguage || k.systemLanguage || "",
                key: "language"
            });
            return a
        }, platformKey: function (a) {
            this.options.excludePlatform || a.push({key: "navigator_platform", value: this.getNavigatorPlatform()});
            return a
        }, getNavigatorPlatform: function () {
            return k.platform ? k.platform : "unknown"
        }, jsFontsKey: function (a, b) {
            var c = this;
            return G(function () {
                function d() {
                    var a = v.createElement("span");
                    a.style.position =
                        "absolute";
                    a.style.left = "-9999px";
                    a.style.fontSize = "72px";
                    a.style.lineHeight = "normal";
                    a.innerHTML = "mmmmmmmmmmlli";
                    return a
                }

                var e = ["monospace", "sans-serif", "serif"],
                    f = "Andale Mono;Arial;Arial Black;Arial Hebrew;Arial MT;Arial Narrow;Arial Rounded MT Bold;Arial Unicode MS;Bitstream Vera Sans Mono;Book Antiqua;Bookman Old Style;Calibri;Cambria;Cambria Math;Century;Century Gothic;Century Schoolbook;Comic Sans;Comic Sans MS;Consolas;Courier;Courier New;Garamond;Geneva;Georgia;Helvetica;Helvetica Neue;Impact;Lucida Bright;Lucida Calligraphy;Lucida Console;Lucida Fax;LUCIDA GRANDE;Lucida Handwriting;Lucida Sans;Lucida Sans Typewriter;Lucida Sans Unicode;Microsoft Sans Serif;Monaco;Monotype Corsiva;MS Gothic;MS Outlook;MS PGothic;MS Reference Sans Serif;MS Sans Serif;MS Serif;MYRIAD;MYRIAD PRO;Palatino;Palatino Linotype;Segoe Print;Segoe Script;Segoe UI;Segoe UI Light;Segoe UI Semibold;Segoe UI Symbol;Tahoma;Times;Times New Roman;Trebuchet MS;Verdana;Wingdings;Wingdings 2;Wingdings 3".split(";"),
                    h = "Abadi MT Condensed Light;Academy Engraved LET;ADOBE CASLON PRO;Adobe Garamond;ADOBE GARAMOND PRO;Agency FB;Aharoni;Albertus Extra Bold;Albertus Medium;Algerian;Amazone BT;American Typewriter;American Typewriter Condensed;AmerType Md BT;Andalus;Angsana New;AngsanaUPC;Antique Olive;Aparajita;Apple Chancery;Apple Color Emoji;Apple SD Gothic Neo;Arabic Typesetting;ARCHER;ARNO PRO;Arrus BT;Aurora Cn BT;AvantGarde Bk BT;AvantGarde Md BT;AVENIR;Ayuthaya;Bandy;Bangla Sangam MN;Bank Gothic;BankGothic Md BT;Baskerville;Baskerville Old Face;Batang;BatangChe;Bauer Bodoni;Bauhaus 93;Bazooka;Bell MT;Bembo;Benguiat Bk BT;Berlin Sans FB;Berlin Sans FB Demi;Bernard MT Condensed;BernhardFashion BT;BernhardMod BT;Big Caslon;BinnerD;Blackadder ITC;BlairMdITC TT;Bodoni 72;Bodoni 72 Oldstyle;Bodoni 72 Smallcaps;Bodoni MT;Bodoni MT Black;Bodoni MT Condensed;Bodoni MT Poster Compressed;Bookshelf Symbol 7;Boulder;Bradley Hand;Bradley Hand ITC;Bremen Bd BT;Britannic Bold;Broadway;Browallia New;BrowalliaUPC;Brush Script MT;Californian FB;Calisto MT;Calligrapher;Candara;CaslonOpnface BT;Castellar;Centaur;Cezanne;CG Omega;CG Times;Chalkboard;Chalkboard SE;Chalkduster;Charlesworth;Charter Bd BT;Charter BT;Chaucer;ChelthmITC Bk BT;Chiller;Clarendon;Clarendon Condensed;CloisterBlack BT;Cochin;Colonna MT;Constantia;Cooper Black;Copperplate;Copperplate Gothic;Copperplate Gothic Bold;Copperplate Gothic Light;CopperplGoth Bd BT;Corbel;Cordia New;CordiaUPC;Cornerstone;Coronet;Cuckoo;Curlz MT;DaunPenh;Dauphin;David;DB LCD Temp;DELICIOUS;Denmark;DFKai-SB;Didot;DilleniaUPC;DIN;DokChampa;Dotum;DotumChe;Ebrima;Edwardian Script ITC;Elephant;English 111 Vivace BT;Engravers MT;EngraversGothic BT;Eras Bold ITC;Eras Demi ITC;Eras Light ITC;Eras Medium ITC;EucrosiaUPC;Euphemia;Euphemia UCAS;EUROSTILE;Exotc350 Bd BT;FangSong;Felix Titling;Fixedsys;FONTIN;Footlight MT Light;Forte;FrankRuehl;Fransiscan;Freefrm721 Blk BT;FreesiaUPC;Freestyle Script;French Script MT;FrnkGothITC Bk BT;Fruitger;FRUTIGER;Futura;Futura Bk BT;Futura Lt BT;Futura Md BT;Futura ZBlk BT;FuturaBlack BT;Gabriola;Galliard BT;Gautami;Geeza Pro;Geometr231 BT;Geometr231 Hv BT;Geometr231 Lt BT;GeoSlab 703 Lt BT;GeoSlab 703 XBd BT;Gigi;Gill Sans;Gill Sans MT;Gill Sans MT Condensed;Gill Sans MT Ext Condensed Bold;Gill Sans Ultra Bold;Gill Sans Ultra Bold Condensed;Gisha;Gloucester MT Extra Condensed;GOTHAM;GOTHAM BOLD;Goudy Old Style;Goudy Stout;GoudyHandtooled BT;GoudyOLSt BT;Gujarati Sangam MN;Gulim;GulimChe;Gungsuh;GungsuhChe;Gurmukhi MN;Haettenschweiler;Harlow Solid Italic;Harrington;Heather;Heiti SC;Heiti TC;HELV;Herald;High Tower Text;Hiragino Kaku Gothic ProN;Hiragino Mincho ProN;Hoefler Text;Humanst 521 Cn BT;Humanst521 BT;Humanst521 Lt BT;Imprint MT Shadow;Incised901 Bd BT;Incised901 BT;Incised901 Lt BT;INCONSOLATA;Informal Roman;Informal011 BT;INTERSTATE;IrisUPC;Iskoola Pota;JasmineUPC;Jazz LET;Jenson;Jester;Jokerman;Juice ITC;Kabel Bk BT;Kabel Ult BT;Kailasa;KaiTi;Kalinga;Kannada Sangam MN;Kartika;Kaufmann Bd BT;Kaufmann BT;Khmer UI;KodchiangUPC;Kokila;Korinna BT;Kristen ITC;Krungthep;Kunstler Script;Lao UI;Latha;Leelawadee;Letter Gothic;Levenim MT;LilyUPC;Lithograph;Lithograph Light;Long Island;Lydian BT;Magneto;Maiandra GD;Malayalam Sangam MN;Malgun Gothic;Mangal;Marigold;Marion;Marker Felt;Market;Marlett;Matisse ITC;Matura MT Script Capitals;Meiryo;Meiryo UI;Microsoft Himalaya;Microsoft JhengHei;Microsoft New Tai Lue;Microsoft PhagsPa;Microsoft Tai Le;Microsoft Uighur;Microsoft YaHei;Microsoft Yi Baiti;MingLiU;MingLiU_HKSCS;MingLiU_HKSCS-ExtB;MingLiU-ExtB;Minion;Minion Pro;Miriam;Miriam Fixed;Mistral;Modern;Modern No. 20;Mona Lisa Solid ITC TT;Mongolian Baiti;MONO;MoolBoran;Mrs Eaves;MS LineDraw;MS Mincho;MS PMincho;MS Reference Specialty;MS UI Gothic;MT Extra;MUSEO;MV Boli;Nadeem;Narkisim;NEVIS;News Gothic;News GothicMT;NewsGoth BT;Niagara Engraved;Niagara Solid;Noteworthy;NSimSun;Nyala;OCR A Extended;Old Century;Old English Text MT;Onyx;Onyx BT;OPTIMA;Oriya Sangam MN;OSAKA;OzHandicraft BT;Palace Script MT;Papyrus;Parchment;Party LET;Pegasus;Perpetua;Perpetua Titling MT;PetitaBold;Pickwick;Plantagenet Cherokee;Playbill;PMingLiU;PMingLiU-ExtB;Poor Richard;Poster;PosterBodoni BT;PRINCETOWN LET;Pristina;PTBarnum BT;Pythagoras;Raavi;Rage Italic;Ravie;Ribbon131 Bd BT;Rockwell;Rockwell Condensed;Rockwell Extra Bold;Rod;Roman;Sakkal Majalla;Santa Fe LET;Savoye LET;Sceptre;Script;Script MT Bold;SCRIPTINA;Serifa;Serifa BT;Serifa Th BT;ShelleyVolante BT;Sherwood;Shonar Bangla;Showcard Gothic;Shruti;Signboard;SILKSCREEN;SimHei;Simplified Arabic;Simplified Arabic Fixed;SimSun;SimSun-ExtB;Sinhala Sangam MN;Sketch Rockwell;Skia;Small Fonts;Snap ITC;Snell Roundhand;Socket;Souvenir Lt BT;Staccato222 BT;Steamer;Stencil;Storybook;Styllo;Subway;Swis721 BlkEx BT;Swiss911 XCm BT;Sylfaen;Synchro LET;System;Tamil Sangam MN;Technical;Teletype;Telugu Sangam MN;Tempus Sans ITC;Terminal;Thonburi;Traditional Arabic;Trajan;TRAJAN PRO;Tristan;Tubular;Tunga;Tw Cen MT;Tw Cen MT Condensed;Tw Cen MT Condensed Extra Bold;TypoUpright BT;Unicorn;Univers;Univers CE 55 Medium;Univers Condensed;Utsaah;Vagabond;Vani;Vijaya;Viner Hand ITC;VisualUI;Vivaldi;Vladimir Script;Vrinda;Westminster;WHITNEY;Wide Latin;ZapfEllipt BT;ZapfHumnst BT;ZapfHumnst Dm BT;Zapfino;Zurich BlkEx BT;Zurich Ex BT;ZWAdobeF".split(";");
                c.options.extendedJsFonts && (f = f.concat(h));
                for (var f = f.concat(c.options.userDefinedFonts), h = v.getElementsByTagName("body")[0], m = v.createElement("div"), k = v.createElement("div"), g = {}, N = {}, l = [], p = 0, n = e.length; p < n; p++) {
                    var q = d();
                    q.style.fontFamily = e[p];
                    m.appendChild(q);
                    l.push(q)
                }
                h.appendChild(m);
                p = 0;
                for (n = e.length; p < n; p++) g[e[p]] = l[p].offsetWidth, N[e[p]] = l[p].offsetHeight;
                l = {};
                p = 0;
                for (n = f.length; p < n; p++) {
                    for (var q = [], r = 0, u = e.length; r < u; r++) {
                        var A;
                        A = f[p];
                        var y = e[r], E = d();
                        E.style.fontFamily = "'" + A + "'," +
                            y;
                        A = E;
                        k.appendChild(A);
                        q.push(A)
                    }
                    l[f[p]] = q
                }
                h.appendChild(k);
                p = [];
                n = 0;
                for (q = f.length; n < q; n++) {
                    r = l[f[n]];
                    u = !1;
                    for (A = 0; A < e.length && !(u = r[A].offsetWidth !== g[e[A]] || r[A].offsetHeight !== N[e[A]]); A++) ;
                    u && p.push(f[n])
                }
                h.removeChild(k);
                h.removeChild(m);
                a.push({value: p, key: "js_fonts"});
                b(a)
            }, 1)
        }, getHasLiedLanguages: function () {
            if ("undefined" !== typeof k.languages) try {
                if (k.languages[0].substr(0, 2) !== k.language.substr(0, 2)) return !0
            } catch (a) {
                return !0
            }
            return !1
        }, colorDepthKey: function (a) {
            this.options.excludeColorDepth ||
            a.push({value: x.colorDepth || -1, key: "color_depth"});
            return a
        }, addBehaviorKey: function (a) {
            v.body && !this.options.excludeAddBehavior && v.body.addBehavior && a.push({key: "add_behavior", value: 1});
            return a
        }, hasLiedOsKey: function (a) {
            this.options.excludeHasLiedOs || a.push({value: this.getHasLiedOs(), key: "has_lied_os"});
            return a
        }, x64Multiply: function (a, b) {
            a = [a[0] >>> 16, a[0] & 65535, a[1] >>> 16, a[1] & 65535];
            b = [b[0] >>> 16, b[0] & 65535, b[1] >>> 16, b[1] & 65535];
            var c = [0, 0, 0, 0];
            c[3] += a[3] * b[3];
            c[2] += c[3] >>> 16;
            c[3] &= 65535;
            c[2] +=
                a[2] * b[3];
            c[1] += c[2] >>> 16;
            c[2] &= 65535;
            c[2] += a[3] * b[2];
            c[1] += c[2] >>> 16;
            c[2] &= 65535;
            c[1] += a[1] * b[3];
            c[0] += c[1] >>> 16;
            c[1] &= 65535;
            c[1] += a[2] * b[2];
            c[0] += c[1] >>> 16;
            c[1] &= 65535;
            c[1] += a[3] * b[1];
            c[0] += c[1] >>> 16;
            c[1] &= 65535;
            c[0] += a[0] * b[3] + a[1] * b[2] + a[2] * b[1] + a[3] * b[0];
            c[0] &= 65535;
            return [c[0] << 16 | c[1], c[2] << 16 | c[3]]
        }, hasIndexedDB: function () {
            return !!r.indexedDB
        }, x64Xor: function (a, b) {
            return [a[0] ^ b[0], a[1] ^ b[1]]
        }, getIEPlugins: function () {
            var a = [];
            if (Object.getOwnPropertyDescriptor && Object.getOwnPropertyDescriptor(r,
                    "ActiveXObject") || "ActiveXObject" in r) a = this.map("AcroPDF.PDF;Adodb.Stream;AgControl.AgControl;DevalVRXCtrl.DevalVRXCtrl.1;MacromediaFlashPaper.MacromediaFlashPaper;Msxml2.DOMDocument;Msxml2.XMLHTTP;PDF.PdfCtrl;QuickTime.QuickTime;QuickTimeCheckObject.QuickTimeCheck.1;RealPlayer;RealPlayer.RealPlayer(tm) ActiveX Control (32-bit);RealVideo.RealVideo(tm) ActiveX Control (32-bit);Scripting.Dictionary;SWCtl.SWCtl;Shell.UIHelper;ShockwaveFlash.ShockwaveFlash;Skype.Detection;TDCCtl.TDCCtl;WMPlayer.OCX;rmocx.RealPlayer G2 Control;rmocx.RealPlayer G2 Control.1".split(";"),
                function (a) {
                    try {
                        return new ActiveXObject(a), a
                    } catch (c) {
                        return null
                    }
                });
            k.plugins && (a = a.concat(this.getRegularPlugins()));
            return a
        }, openDatabaseKey: function (a) {
            !this.options.excludeOpenDatabase && r.openDatabase && a.push({value: 1, key: "open_database"});
            return a
        }, availableScreenResolutionKey: function (a) {
            return this.options.excludeAvailableScreenResolution ? a : this.getAvailableScreenResolution(a)
        }, getNavigatorCpuClass: function () {
            return k.cpuClass ? k.cpuClass : "unknown"
        }, map: function (a, b, c) {
            var d = [];
            if (null ==
                a) return d;
            if (this.nativeMap && a.map === this.nativeMap) return a.map(b, c);
            this.each(a, function (a, f, h) {
                d[d.length] = b.call(c, a, f, h)
            });
            return d
        }, adBlockKey: function (a) {
            this.options.excludeAdBlock || a.push({value: this.getAdBlock(), key: "adblock"});
            return a
        }, getUserAgent: function () {
            var a = k.userAgent;
            return a = a.replace(/\&|\+|\?|\%|\#|\/|\=/g, "")
        }, webglKey: function (a) {
            if (this.options.excludeWebGL || !this.isWebGlSupported()) return a;
            a.push({key: "webgl", value: this.getWebglFp()});
            return a
        }, getDoNotTrack: function () {
            return k.doNotTrack ?
                k.doNotTrack : k.msDoNotTrack ? k.msDoNotTrack : r.doNotTrack ? r.doNotTrack : "unknown"
        }, loadSwfAndDetectFonts: function (a) {
            r.___fp_swf_loaded = function (b) {
                a(b)
            };
            var b = this.options.swfContainerId;
            this.addFlashDivNode();
            Y.embedSWF(this.options.swfPath, b, "1", "1", "9.0.0", !1, {onReady: "___fp_swf_loaded"}, {
                menu: "false",
                allowScriptAccess: "always"
            }, {})
        }, sessionStorageKey: function (a) {
            !this.options.excludeSessionStorage && this.hasSessionStorage() && a.push({
                value: 1,
                key: "session_storage"
            });
            return a
        }, getRegularPlugins: function () {
            for (var a =
                [], b = 0, c = k.plugins.length; b < c; b++) a.push(k.plugins[b]);
            this.pluginsShouldBeSorted() && (a = a.sort(function (a, b) {
                return a.name > b.name ? 1 : a.name < b.name ? -1 : 0
            }));
            return this.map(a, function (a) {
                var b = this.map(a, function (a) {
                    return [a.type, a.suffixes].join("~")
                }).join(",");
                return [a.name, a.description, b].join("::")
            }, this)
        }, canvasKey: function (a) {
            !this.options.excludeCanvas && this.isCanvasSupported() && a.push({
                key: "canvas",
                value: this.getCanvasFp()
            });
            return a
        }, flashFontsKey: function (a, b) {
            if (this.options.excludeFlashFonts ||
                !this.hasSwfObjectLoaded() || !this.hasMinFlashInstalled() || "undefined" === typeof this.options.swfPath) return b(a);
            this.loadSwfAndDetectFonts(function (c) {
                a.push({key: "swf_fonts", value: c.join(";")});
                b(a)
            })
        }, x64Add: function (a, b) {
            a = [a[0] >>> 16, a[0] & 65535, a[1] >>> 16, a[1] & 65535];
            b = [b[0] >>> 16, b[0] & 65535, b[1] >>> 16, b[1] & 65535];
            var c = [0, 0, 0, 0];
            c[3] += a[3] + b[3];
            c[2] += c[3] >>> 16;
            c[3] &= 65535;
            c[2] += a[2] + b[2];
            c[1] += c[2] >>> 16;
            c[2] &= 65535;
            c[1] += a[1] + b[1];
            c[0] += c[1] >>> 16;
            c[1] &= 65535;
            c[0] += a[0] + b[0];
            c[0] &= 65535;
            return [c[0] <<
            16 | c[1], c[2] << 16 | c[3]]
        }, getHasLiedResolution: function () {
            return x.width < x.availWidth || x.height < x.availHeight ? !0 : !1
        }, pluginsShouldBeSorted: function () {
            for (var a = !1, b = 0, c = this.options.sortPluginsFor.length; b < c; b++) if (k.userAgent.match(this.options.sortPluginsFor[b])) {
                a = !0;
                break
            }
            return a
        }, fontsKey: function (a, b) {
            return this.options.excludeJsFonts ? this.flashFontsKey(a, b) : this.jsFontsKey(a, b)
        }, hasSessionStorage: function () {
            try {
                return !!r.sessionStorage
            } catch (a) {
                return !0
            }
        }, pluginsKey: function (a) {
            this.options.excludePlugins ||
            (this.isIE() ? this.options.excludeIEPlugins || a.push({
                key: "ie_plugins",
                value: this.getIEPlugins()
            }) : a.push({key: "regular_plugins", value: this.getRegularPlugins()}));
            return a
        }, getWebglCanvas: function () {
            var a = v.createElement("canvas"), b = null;
            try {
                b = a.getContext("webgl") || a.getContext("experimental-webgl")
            } catch (c) {
            }
            b || (b = null);
            return b
        }, x64Fmix: function (a) {
            a = this.x64Xor(a, [0, a[0] >>> 1]);
            a = this.x64Multiply(a, [4283543511, 3981806797]);
            a = this.x64Xor(a, [0, a[0] >>> 1]);
            a = this.x64Multiply(a, [3301882366, 444984403]);
            return a = this.x64Xor(a, [0, a[0] >>> 1])
        }, hasLocalStorage: function () {
            try {
                return !!r.localStorage
            } catch (a) {
                return !0
            }
        }, isIE: function () {
            return "Microsoft Internet Explorer" === k.appName || "Netscape" === k.appName && /Trident/.test(k.userAgent) ? !0 : !1
        }, getTouchSupport: function () {
            var a = 0, b = !1;
            "undefined" !== typeof k.maxTouchPoints ? a = k.maxTouchPoints : "undefined" !== typeof k.msMaxTouchPoints && (a = k.msMaxTouchPoints);
            try {
                v.createEvent("TouchEvent"), b = !0
            } catch (c) {
            }
            return [a, b, "ontouchstart" in r]
        }, timezoneOffsetKey: function (a) {
            this.options.excludeTimezoneOffset ||
            a.push({value: (new Date).getTimezoneOffset(), key: "timezone_offset"});
            return a
        }, doNotTrackKey: function (a) {
            this.options.excludeDoNotTrack || a.push({value: this.getDoNotTrack(), key: "do_not_track"});
            return a
        }, pixelRatioKey: function (a) {
            this.options.excludePixelRatio || a.push({key: "pixel_ratio", value: this.getPixelRatio()});
            return a
        }, x64Rotl: function (a, b) {
            b %= 64;
            if (32 === b) return [a[1], a[0]];
            if (32 > b) return [a[0] << b | a[1] >>> 32 - b, a[1] << b | a[0] >>> 32 - b];
            b -= 32;
            return [a[1] << b | a[0] >>> 32 - b, a[0] << b | a[1] >>> 32 - b]
        }, getScreenResolution: function (a) {
            var b;
            b = this.options.detectScreenOrientation ? x.height > x.width ? [x.height, x.width] : [x.width, x.height] : [x.width, x.height];
            "undefined" !== typeof b && a.push({value: b, key: "resolution"});
            return a
        }, getAdBlock: function () {
            var a = v.createElement("div");
            a.innerHTML = "\x26nbsp;";
            a.className = "adsbox";
            var b = "0";
            try {
                v.body.appendChild(a), 0 === v.getElementsByClassName("adsbox")[0].offsetHeight && (b = "1"), v.body.removeChild(a)
            } catch (c) {
                b = "0"
            }
            return b
        }, cpuClassKey: function (a) {
            this.options.excludeCpuClass || a.push({
                value: this.getNavigatorCpuClass(),
                key: "cpu_class"
            });
            return a
        }, x64hash128: function (a, b) {
            a = a || "";
            b = b || 0;
            for (var c = a.length % 16, d = a.length - c, e = [0, b], f = [0, b], h, m, k = [2277735313, 289559509], n = [1291169091, 658871167], g = 0; g < d; g += 16) h = [a.charCodeAt(g + 4) & 255 | (a.charCodeAt(g + 5) & 255) << 8 | (a.charCodeAt(g + 6) & 255) << 16 | (a.charCodeAt(g + 7) & 255) << 24, a.charCodeAt(g) & 255 | (a.charCodeAt(g + 1) & 255) << 8 | (a.charCodeAt(g + 2) & 255) << 16 | (a.charCodeAt(g + 3) & 255) << 24], m = [a.charCodeAt(g + 12) & 255 | (a.charCodeAt(g + 13) & 255) << 8 | (a.charCodeAt(g + 14) & 255) << 16 | (a.charCodeAt(g + 15) &
                255) << 24, a.charCodeAt(g + 8) & 255 | (a.charCodeAt(g + 9) & 255) << 8 | (a.charCodeAt(g + 10) & 255) << 16 | (a.charCodeAt(g + 11) & 255) << 24], h = this.x64Multiply(h, k), h = this.x64Rotl(h, 31), h = this.x64Multiply(h, n), e = this.x64Xor(e, h), e = this.x64Rotl(e, 27), e = this.x64Add(e, f), e = this.x64Add(this.x64Multiply(e, [0, 5]), [0, 1390208809]), m = this.x64Multiply(m, n), m = this.x64Rotl(m, 33), m = this.x64Multiply(m, k), f = this.x64Xor(f, m), f = this.x64Rotl(f, 31), f = this.x64Add(f, e), f = this.x64Add(this.x64Multiply(f, [0, 5]), [0, 944331445]);
            h = [0, 0];
            m = [0, 0];
            switch (c) {
                case 15:
                    m =
                        this.x64Xor(m, this.x64LeftShift([0, a.charCodeAt(g + 14)], 48));
                case 14:
                    m = this.x64Xor(m, this.x64LeftShift([0, a.charCodeAt(g + 13)], 40));
                case 13:
                    m = this.x64Xor(m, this.x64LeftShift([0, a.charCodeAt(g + 12)], 32));
                case 12:
                    m = this.x64Xor(m, this.x64LeftShift([0, a.charCodeAt(g + 11)], 24));
                case 11:
                    m = this.x64Xor(m, this.x64LeftShift([0, a.charCodeAt(g + 10)], 16));
                case 10:
                    m = this.x64Xor(m, this.x64LeftShift([0, a.charCodeAt(g + 9)], 8));
                case 9:
                    m = this.x64Xor(m, [0, a.charCodeAt(g + 8)]), m = this.x64Multiply(m, n), m = this.x64Rotl(m, 33), m =
                        this.x64Multiply(m, k), f = this.x64Xor(f, m);
                case 8:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g + 7)], 56));
                case 7:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g + 6)], 48));
                case 6:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g + 5)], 40));
                case 5:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g + 4)], 32));
                case 4:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g + 3)], 24));
                case 3:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g + 2)], 16));
                case 2:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(g +
                        1)], 8));
                case 1:
                    h = this.x64Xor(h, [0, a.charCodeAt(g)]), h = this.x64Multiply(h, k), h = this.x64Rotl(h, 31), h = this.x64Multiply(h, n), e = this.x64Xor(e, h)
            }
            e = this.x64Xor(e, [0, a.length]);
            f = this.x64Xor(f, [0, a.length]);
            e = this.x64Add(e, f);
            f = this.x64Add(f, e);
            e = this.x64Fmix(e);
            f = this.x64Fmix(f);
            e = this.x64Add(e, f);
            f = this.x64Add(f, e);
            return ("00000000" + (e[0] >>> 0).toString(16)).slice(-8) + ("00000000" + (e[1] >>> 0).toString(16)).slice(-8) + ("00000000" + (f[0] >>> 0).toString(16)).slice(-8) + ("00000000" + (f[1] >>> 0).toString(16)).slice(-8)
        },
        getCanvasFp: function () {
            var a = [], b = v.createElement("canvas");
            b.width = 2E3;
            b.height = 200;
            b.style.display = "inline";
            var c = b.getContext("2d");
            c.rect(0, 0, 10, 10);
            c.rect(2, 2, 6, 6);
            a.push("canvas winding:" + (!1 === c.isPointInPath(5, 5, "evenodd") ? "yes" : "no"));
            c.textBaseline = "alphabetic";
            c.fillStyle = "#f60";
            c.fillRect(125, 1, 62, 20);
            c.fillStyle = "#069";
            c.font = this.options.dontUseFakeFontInCanvas ? "11pt Arial" : "11pt no-real-font-123";
            c.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 2, 15);
            c.fillStyle = "rgba(102, 204, 0, 0.2)";
            c.font = "18pt Arial";
            c.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 4, 45);
            c.globalCompositeOperation = "multiply";
            c.fillStyle = "rgb(255,0,255)";
            c.beginPath();
            c.arc(50, 50, 50, 0, 2 * Math.PI, !0);
            c.closePath();
            c.fill();
            c.fillStyle = "rgb(0,255,255)";
            c.beginPath();
            c.arc(100, 50, 50, 0, 2 * Math.PI, !0);
            c.closePath();
            c.fill();
            c.fillStyle = "rgb(255,255,0)";
            c.beginPath();
            c.arc(75, 100, 50, 0, 2 * Math.PI, !0);
            c.closePath();
            c.fill();
            c.fillStyle = "rgb(255,0,255)";
            c.arc(75, 75, 75, 0, 2 * Math.PI, !0);
            c.arc(75, 75, 25, 0, 2 * Math.PI,
                !0);
            c.fill("evenodd");
            a.push("canvas fp:" + b.toDataURL());
            return a.join("~")
        }, x64LeftShift: function (a, b) {
            b %= 64;
            return 0 === b ? a : 32 > b ? [a[0] << b | a[1] >>> 32 - b, a[1] << b] : [a[1] << b - 32, 0]
        }, getPixelRatio: function () {
            return r.devicePixelRatio || ""
        }, addFlashDivNode: function () {
            var a = v.createElement("div");
            a.setAttribute("id", this.options.swfContainerId);
            v.body.appendChild(a)
        }, indexedDbKey: function (a) {
            !this.options.excludeIndexedDB && this.hasIndexedDB() && a.push({key: "indexed_db", value: 1});
            return a
        }, hasLiedBrowserKey: function (a) {
            this.options.excludeHasLiedBrowser ||
            a.push({value: this.getHasLiedBrowser(), key: "has_lied_browser"});
            return a
        }, each: function (a, b, c) {
            if (null !== a) if (this.nativeForEach && a.forEach === this.nativeForEach) a.forEach(b, c); else if (a.length === +a.length) for (var d = 0, e = a.length; d < e && b.call(c, a[d], d, a) !== {}; d++) ; else for (d in a) if (a.hasOwnProperty(d) && b.call(c, a[d], d, a) === {}) break
        }, getHasLiedOs: function () {
            var a = k.userAgent.toLowerCase(), b = k.oscpu, c = k.platform.toLowerCase(),
                a = 0 <= a.indexOf("windows phone") ? "Windows Phone" : 0 <= a.indexOf("win") ? "Windows" :
                    0 <= a.indexOf("android") ? "Android" : 0 <= a.indexOf("linux") ? "Linux" : 0 <= a.indexOf("iphone") || 0 <= a.indexOf("ipad") ? "iOS" : 0 <= a.indexOf("mac") ? "Mac" : "Other";
            return ("ontouchstart" in r || 0 < k.maxTouchPoints || 0 < k.msMaxTouchPoints) && "Windows Phone" !== a && "Android" !== a && "iOS" !== a && "Other" !== a || "undefined" !== typeof b && (b = b.toLowerCase(), 0 <= b.indexOf("win") && "Windows" !== a && "Windows Phone" !== a || 0 <= b.indexOf("linux") && "Linux" !== a && "Android" !== a || 0 <= b.indexOf("mac") && "Mac" !== a && "iOS" !== a || 0 === b.indexOf("win") && 0 === b.indexOf("linux") &&
            0 <= b.indexOf("mac") && "other" !== a) ? !0 : 0 <= c.indexOf("win") && "Windows" !== a && "Windows Phone" !== a || (0 <= c.indexOf("linux") || 0 <= c.indexOf("android") || 0 <= c.indexOf("pike")) && "Linux" !== a && "Android" !== a || (0 <= c.indexOf("mac") || 0 <= c.indexOf("ipad") || 0 <= c.indexOf("ipod") || 0 <= c.indexOf("iphone")) && "Mac" !== a && "iOS" !== a || 0 === c.indexOf("win") && 0 === c.indexOf("linux") && 0 <= c.indexOf("mac") && "other" !== a ? !0 : "undefined" === typeof k.plugins && "Windows" !== a && "Windows Phone" !== a ? !0 : !1
        }, hasLiedLanguagesKey: function (a) {
            this.options.excludeHasLiedLanguages ||
            a.push({key: "has_lied_languages", value: this.getHasLiedLanguages()});
            return a
        }, isCanvasSupported: function () {
            var a = v.createElement("canvas");
            return !(!a.getContext || !a.getContext("2d"))
        }, hasMinFlashInstalled: function () {
            return Y.hasFlashPlayerVersion("9.0.0")
        }, getAvailableScreenResolution: function (a) {
            var b;
            x.availWidth && x.availHeight && (b = this.options.detectScreenOrientation ? x.availHeight > x.availWidth ? [x.availHeight, x.availWidth] : [x.availWidth, x.availHeight] : [x.availHeight, x.availWidth]);
            "undefined" !==
            typeof b && a.push({value: b, key: "available_resolution"});
            return a
        }, hasSwfObjectLoaded: function () {
            return "undefined" !== typeof r.swfobject
        }, getHasLiedBrowser: function () {
            var a = k.userAgent.toLowerCase(), b = k.productSub,
                a = 0 <= a.indexOf("firefox") ? "Firefox" : 0 <= a.indexOf("opera") || 0 <= a.indexOf("opr") ? "Opera" : 0 <= a.indexOf("chrome") ? "Chrome" : 0 <= a.indexOf("safari") ? "Safari" : 0 <= a.indexOf("trident") ? "Internet Explorer" : "Other";
            if (("Chrome" === a || "Safari" === a || "Opera" === a) && "20030107" !== b) return !0;
            b = eval.toString().length;
            if (37 === b && "Safari" !== a && "Firefox" !== a && "Other" !== a || 39 === b && "Internet Explorer" !== a && "Other" !== a || 33 === b && "Chrome" !== a && "Opera" !== a && "Other" !== a) return !0;
            var c;
            try {
                throw"a";
            } catch (d) {
                try {
                    d.toSource(), c = !0
                } catch (e) {
                    c = !1
                }
            }
            return c && "Firefox" !== a && "Other" !== a ? !0 : !1
        }, localStorageKey: function (a) {
            !this.options.excludeSessionStorage && this.hasLocalStorage() && a.push({key: "local_storage", value: 1});
            return a
        }, touchSupportKey: function (a) {
            this.options.excludeTouchSupport || a.push({
                value: this.getTouchSupport(),
                key: "touch_support"
            });
            return a
        }, extend: function (a, b) {
            if (null == a) return b;
            for (var c in a) null != a[c] && b[c] !== a[c] && (b[c] = a[c]);
            return b
        }, isWebGlSupported: function () {
            if (!this.isCanvasSupported()) return !1;
            var a = v.createElement("canvas"), b;
            try {
                b = a.getContext && (a.getContext("webgl") || a.getContext("experimental-webgl"))
            } catch (c) {
                b = !1
            }
            return !!r.WebGLRenderingContext && !!b
        }, get: function (a) {
            var b = [], b = this.userAgentKey(b), b = this.languageKey(b), b = this.colorDepthKey(b),
                b = this.pixelRatioKey(b), b = this.screenResolutionKey(b),
                b = this.availableScreenResolutionKey(b), b = this.timezoneOffsetKey(b), b = this.sessionStorageKey(b),
                b = this.localStorageKey(b), b = this.indexedDbKey(b), b = this.addBehaviorKey(b),
                b = this.openDatabaseKey(b), b = this.cpuClassKey(b), b = this.platformKey(b),
                b = this.doNotTrackKey(b), b = this.pluginsKey(b), b = this.canvasKey(b), b = this.webglKey(b),
                b = this.adBlockKey(b), b = this.hasLiedLanguagesKey(b), b = this.hasLiedResolutionKey(b),
                b = this.hasLiedOsKey(b), b = this.hasLiedBrowserKey(b), b = this.touchSupportKey(b), c = this;
            this.fontsKey(b,
                function (b) {
                    var d = [];
                    c.each(b, function (a) {
                        var b = a.value;
                        "undefined" !== typeof a.value.join && (b = a.value.join(";"));
                        d.push(b)
                    });
                    var f = c.x64hash128(d.join("~~~"), 31);
                    return a(f, b)
                })
        }, hasLiedResolutionKey: function (a) {
            this.options.excludeHasLiedResolution || a.push({
                value: this.getHasLiedResolution(),
                key: "has_lied_resolution"
            });
            return a
        }, screenResolutionKey: function (a) {
            return this.options.excludeScreenResolution ? a : this.getScreenResolution(a)
        }, userAgentKey: function (a) {
            this.options.excludeUserAgent || a.push({
                key: "user_agent",
                value: this.getUserAgent()
            });
            return a
        }, getWebglFp: function () {
            function a(a) {
                b.clearColor(0, 0, 0, 1);
                b.enable(b.DEPTH_TEST);
                b.depthFunc(b.LEQUAL);
                b.clear(b.COLOR_BUFFER_BIT | b.DEPTH_BUFFER_BIT);
                return "[" + a[0] + ", " + a[1] + "]"
            }

            var b;
            b = this.getWebglCanvas();
            if (!b) return null;
            var c = [], d = b.createBuffer();
            b.bindBuffer(b.ARRAY_BUFFER, d);
            var e = new Float32Array([-.2, -.9, 0, .4, -.26, 0, 0, .732134444, 0]);
            b.bufferData(b.ARRAY_BUFFER, e, b.STATIC_DRAW);
            d.itemSize = 3;
            d.numItems = 3;
            var e = b.createProgram(), f = b.createShader(b.VERTEX_SHADER);
            b.shaderSource(f, "attribute vec2 attrVertex;varying vec2 varyinTexCoordinate;uniform vec2 uniformOffset;void main(){varyinTexCoordinate\x3dattrVertex+uniformOffset;gl_Position\x3dvec4(attrVertex,0,1);}");
            b.compileShader(f);
            var h = b.createShader(b.FRAGMENT_SHADER);
            b.shaderSource(h, "precision mediump float;varying vec2 varyinTexCoordinate;void main() {gl_FragColor\x3dvec4(varyinTexCoordinate,0,1);}");
            b.compileShader(h);
            b.attachShader(e, f);
            b.attachShader(e, h);
            b.linkProgram(e);
            b.useProgram(e);
            e.vertexPosAttrib =
                b.getAttribLocation(e, "attrVertex");
            e.offsetUniform = b.getUniformLocation(e, "uniformOffset");
            b.enableVertexAttribArray(e.vertexPosArray);
            b.vertexAttribPointer(e.vertexPosAttrib, d.itemSize, b.FLOAT, !1, 0, 0);
            b.uniform2f(e.offsetUniform, 1, 1);
            b.drawArrays(b.TRIANGLE_STRIP, 0, d.numItems);
            null != b.canvas && c.push(b.canvas.toDataURL());
            c.push("extensions:" + b.getSupportedExtensions().join(";"));
            c.push("webgl aliased line width range:" + a(b.getParameter(b.ALIASED_LINE_WIDTH_RANGE)));
            c.push("webgl aliased point size range:" +
                a(b.getParameter(b.ALIASED_POINT_SIZE_RANGE)));
            c.push("webgl alpha bits:" + b.getParameter(b.ALPHA_BITS));
            c.push("webgl antialiasing:" + (b.getContextAttributes().antialias ? "yes" : "no"));
            c.push("webgl blue bits:" + b.getParameter(b.BLUE_BITS));
            c.push("webgl depth bits:" + b.getParameter(b.DEPTH_BITS));
            c.push("webgl green bits:" + b.getParameter(b.GREEN_BITS));
            c.push("webgl max anisotropy:" + function (a) {
                var b,
                    c = a.getExtension("EXT_texture_filter_anisotropic") || a.getExtension("WEBKIT_EXT_texture_filter_anisotropic") ||
                        a.getExtension("MOZ_EXT_texture_filter_anisotropic");
                return c ? (b = a.getParameter(c.MAX_TEXTURE_MAX_ANISOTROPY_EXT), 0 === b && (b = 2), b) : null
            }(b));
            c.push("webgl max combined texture image units:" + b.getParameter(b.MAX_COMBINED_TEXTURE_IMAGE_UNITS));
            c.push("webgl max cube map texture size:" + b.getParameter(b.MAX_CUBE_MAP_TEXTURE_SIZE));
            c.push("webgl max fragment uniform vectors:" + b.getParameter(b.MAX_FRAGMENT_UNIFORM_VECTORS));
            c.push("webgl max render buffer size:" + b.getParameter(b.MAX_RENDERBUFFER_SIZE));
            c.push("webgl max texture image units:" + b.getParameter(b.MAX_TEXTURE_IMAGE_UNITS));
            c.push("webgl max texture size:" + b.getParameter(b.MAX_TEXTURE_SIZE));
            c.push("webgl max varying vectors:" + b.getParameter(b.MAX_VARYING_VECTORS));
            c.push("webgl max vertex attribs:" + b.getParameter(b.MAX_VERTEX_ATTRIBS));
            c.push("webgl max vertex texture image units:" + b.getParameter(b.MAX_VERTEX_TEXTURE_IMAGE_UNITS));
            c.push("webgl max vertex uniform vectors:" + b.getParameter(b.MAX_VERTEX_UNIFORM_VECTORS));
            c.push("webgl max viewport dims:" +
                a(b.getParameter(b.MAX_VIEWPORT_DIMS)));
            c.push("webgl red bits:" + b.getParameter(b.RED_BITS));
            c.push("webgl renderer:" + b.getParameter(b.RENDERER));
            c.push("webgl shading language version:" + b.getParameter(b.SHADING_LANGUAGE_VERSION));
            c.push("webgl stencil bits:" + b.getParameter(b.STENCIL_BITS));
            c.push("webgl vendor:" + b.getParameter(b.VENDOR));
            c.push("webgl version:" + b.getParameter(b.VERSION));
            if (!b.getShaderPrecisionFormat) return c.join("~");
            c.push("webgl vertex shader high float precision:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER,
                b.HIGH_FLOAT).precision);
            c.push("webgl vertex shader high float precision rangeMin:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.HIGH_FLOAT).rangeMin);
            c.push("webgl vertex shader high float precision rangeMax:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.HIGH_FLOAT).rangeMax);
            c.push("webgl vertex shader medium float precision:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.MEDIUM_FLOAT).precision);
            c.push("webgl vertex shader medium float precision rangeMin:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER,
                b.MEDIUM_FLOAT).rangeMin);
            c.push("webgl vertex shader medium float precision rangeMax:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.MEDIUM_FLOAT).rangeMax);
            c.push("webgl vertex shader low float precision:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.LOW_FLOAT).precision);
            c.push("webgl vertex shader low float precision rangeMin:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.LOW_FLOAT).rangeMin);
            c.push("webgl vertex shader low float precision rangeMax:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER,
                b.LOW_FLOAT).rangeMax);
            c.push("webgl fragment shader high float precision:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.HIGH_FLOAT).precision);
            c.push("webgl fragment shader high float precision rangeMin:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.HIGH_FLOAT).rangeMin);
            c.push("webgl fragment shader high float precision rangeMax:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.HIGH_FLOAT).rangeMax);
            c.push("webgl fragment shader medium float precision:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER,
                b.MEDIUM_FLOAT).precision);
            c.push("webgl fragment shader medium float precision rangeMin:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.MEDIUM_FLOAT).rangeMin);
            c.push("webgl fragment shader medium float precision rangeMax:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.MEDIUM_FLOAT).rangeMax);
            c.push("webgl fragment shader low float precision:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.LOW_FLOAT).precision);
            c.push("webgl fragment shader low float precision rangeMin:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER,
                b.LOW_FLOAT).rangeMin);
            c.push("webgl fragment shader low float precision rangeMax:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.LOW_FLOAT).rangeMax);
            c.push("webgl vertex shader high int precision:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.HIGH_INT).precision);
            c.push("webgl vertex shader high int precision rangeMin:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.HIGH_INT).rangeMin);
            c.push("webgl vertex shader high int precision rangeMax:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.HIGH_INT).rangeMax);
            c.push("webgl vertex shader medium int precision:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.MEDIUM_INT).precision);
            c.push("webgl vertex shader medium int precision rangeMin:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.MEDIUM_INT).rangeMin);
            c.push("webgl vertex shader medium int precision rangeMax:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.MEDIUM_INT).rangeMax);
            c.push("webgl vertex shader low int precision:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.LOW_INT).precision);
            c.push("webgl vertex shader low int precision rangeMin:" +
                b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.LOW_INT).rangeMin);
            c.push("webgl vertex shader low int precision rangeMax:" + b.getShaderPrecisionFormat(b.VERTEX_SHADER, b.LOW_INT).rangeMax);
            c.push("webgl fragment shader high int precision:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.HIGH_INT).precision);
            c.push("webgl fragment shader high int precision rangeMin:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.HIGH_INT).rangeMin);
            c.push("webgl fragment shader high int precision rangeMax:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER,
                b.HIGH_INT).rangeMax);
            c.push("webgl fragment shader medium int precision:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.MEDIUM_INT).precision);
            c.push("webgl fragment shader medium int precision rangeMin:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.MEDIUM_INT).rangeMin);
            c.push("webgl fragment shader medium int precision rangeMax:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.MEDIUM_INT).rangeMax);
            c.push("webgl fragment shader low int precision:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER,
                b.LOW_INT).precision);
            c.push("webgl fragment shader low int precision rangeMin:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.LOW_INT).rangeMin);
            c.push("webgl fragment shader low int precision rangeMax:" + b.getShaderPrecisionFormat(b.FRAGMENT_SHADER, b.LOW_INT).rangeMax);
            return c.join("~")
        }
    };
    var Db = ["scrAvailWidth", "scrAvailHeight"], rb = 0;
    aa.VERSION = "1.4.2";
    var Y, I = r, q = v, O = k, gb = !1, oa = [function () {
            if (gb) {
                var a = q.getElementsByTagName("body")[0], b = q.createElement("object");
                b.setAttribute("type", "application/x-shockwave-flash");
                var c = a.appendChild(b);
                if (c) {
                    var d = 0;
                    (function f() {
                        if ("undefined" != typeof c.GetVariable) {
                            var h = c.GetVariable("$version");
                            h && (h = h.split(" ")[1].split(","), g.pv = [parseInt(h[0], 10), parseInt(h[1], 10), parseInt(h[2], 10)])
                        } else if (10 > d) {
                            d++;
                            G(f, 10);
                            return
                        }
                        a.removeChild(b);
                        c = null;
                        Fa()
                    })()
                } else Fa()
            } else Fa()
        }], L = [], la = [], U = [], Z, na, Ca, Va, Q = !1, ma = !1, K, ya, Sa = !0, g,
        Eb = "undefined" != typeof q.getElementById && "undefined" != typeof q.getElementsByTagName && "undefined" != typeof q.createElement,
        va = O.userAgent.toLowerCase(),
        wa = O.platform.toLowerCase(), Fb = wa ? /win/.test(wa) : /win/.test(va),
        Gb = wa ? /mac/.test(wa) : /mac/.test(va),
        Hb = /webkit/.test(va) ? parseFloat(va.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : !1, La = !+"\x0B1",
        ha = [0, 0, 0], H = null;
    if ("undefined" != typeof O.plugins && "object" == typeof O.plugins["Shockwave Flash"]) !(H = O.plugins["Shockwave Flash"].description) || "undefined" != typeof O.mimeTypes && O.mimeTypes["application/x-shockwave-flash"] && !O.mimeTypes["application/x-shockwave-flash"].enabledPlugin || (gb = !0, La = !1, H = H.replace(/^.*\s+(\S+\s+\S+$)/,
        "$1"), ha[0] = parseInt(H.replace(/^(.*)\..*$/, "$1"), 10), ha[1] = parseInt(H.replace(/^.*\.(.*)\s.*$/, "$1"), 10), ha[2] = /[a-zA-Z]/.test(H) ? parseInt(H.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0); else if ("undefined" != typeof I.ActiveXObject) try {
        if (H = (new ActiveXObject("ShockwaveFlash.ShockwaveFlash")).GetVariable("$version")) La = !0, H = H.split(" ")[1].split(","), ha = [parseInt(H[0], 10), parseInt(H[1], 10), parseInt(H[2], 10)]
    } catch (a) {
    }
    g = {win: Fb, ie: La, mac: Gb, pv: ha, wk: Hb, w3: Eb};
    g.w3 && (("undefined" != typeof q.readyState &&
        "complete" == q.readyState || "undefined" == typeof q.readyState && (q.getElementsByTagName("body")[0] || q.body)) && V(), Q || ("undefined" != typeof q.addEventListener && q.addEventListener("DOMContentLoaded", V, !1), g.ie && g.win && (q.attachEvent("onreadystatechange", function b() {
        "complete" == q.readyState && (q.detachEvent("onreadystatechange", b), V())
    }), I == sb && function c() {
        if (!Q) {
            try {
                q.documentElement.doScroll("left")
            } catch (d) {
                G(c, 0);
                return
            }
            V()
        }
    }()), g.wk && function b() {
        Q || (/loaded|complete/.test(q.readyState) ? V() : G(b, 0))
    }(),
        Wa(V)));
    g.ie && g.win && r.attachEvent("onunload", function () {
        for (var a = U.length, b = 0; b < a; b++) U[b][0].detachEvent(U[b][1], U[b][2]);
        a = la.length;
        for (b = 0; b < a; b++) Ua(la[b]);
        for (var c in g) g[c] = null;
        g = null;
        for (var d in Y) Y[d] = null;
        Y = null
    });
    Y = {
        embedSWF: function (a, b, c, d, e, f, h, m, k, n) {
            var q = {id: b, success: !1};
            g.w3 && !(g.wk && 312 > g.wk) && a && b && c && d && e ? (P(b, !1), Xa(function () {
                c += "";
                d += "";
                var l = {};
                if (k && "object" === typeof k) for (var g in k) l[g] = k[g];
                l.data = a;
                l.width = c;
                l.height = d;
                g = {};
                if (m && "object" === typeof m) for (var r in m) g[r] =
                    m[r];
                if (h && "object" === typeof h) for (var t in h) g.flashvars = "undefined" != typeof g.flashvars ? g.flashvars + ("\x26" + t + "\x3d" + h[t]) : t + "\x3d" + h[t];
                if (ka(e)) r = za(l, g, b), l.id == b && P(b, !0), q.success = !0, q.ref = r; else {
                    if (f && Da()) {
                        l.data = f;
                        Ba(l, g, b, n);
                        return
                    }
                    P(b, !0)
                }
                n && n(q)
            })) : n && n(q)
        }, showExpressInstall: function (a, b, c, d) {
            g.w3 && Da() && Ba(a, b, c, d)
        }, getFlashPlayerVersion: function () {
            return {minor: g.pv[1], major: g.pv[0], release: g.pv[2]}
        }, addDomLoadEvent: Xa, getObjectById: function (a) {
            if (g.w3) return Ea(a)
        }, hasFlashPlayerVersion: ka,
        getQueryParamValue: function (a) {
            var b = q.location.search || q.location.hash;
            if (b) {
                /\?/.test(b) && (b = b.split("?")[1]);
                if (null == a) return Ra(b);
                for (var b = b.split("\x26"), c = 0; c < b.length; c++) if (b[c].substring(0, b[c].indexOf("\x3d")) == a) return Ra(b[c].substring(b[c].indexOf("\x3d") + 1))
            }
            return ""
        }, createSWF: function (a, b, c) {
            if (g.w3) return za(a, b, c)
        }, expressInstallCallback: function () {
            if (ma) {
                var a = J("SWFObjectExprInst");
                a && Z && (a.parentNode.replaceChild(Z, a), na && (P(na, !0), g.ie && g.win && (Z.style.display = "block")), Ca &&
                Ca(Va));
                ma = !1
            }
        }, addLoadEvent: Wa, registerObject: function (a, b, c, d) {
            if (g.w3 && a && b) {
                var e = {};
                e.id = a;
                e.swfVersion = b;
                e.expressInstall = c;
                e.callbackFn = d;
                L[L.length] = e;
                P(a, !1)
            } else d && d({id: a, success: !1})
        }, createCSS: function (a, b, c, d) {
            g.w3 && Ta(a, b, c, d)
        }, removeSWF: function (a) {
            g.w3 && Ua(a)
        }, switchOffAutoHideShow: function () {
            Sa = !1
        }, ua: g
    };
    var Ib = ["sessionStorage", "localStorage", "indexedDb", "openDatabase"], hb = {
        scrAvailSize: "TeRS",
        online: "9vyE",
        browserLanguage: "q4f3",
        indexedDb: "3sw-",
        userLanguage: "hLzX",
        hasLiedResolution: "3neK",
        flashVersion: "dzuS",
        localCode: "lEnu",
        scrAvailHeight: "88tV",
        scrAvailWidth: "E-lJ",
        scrWidth: "ssI5",
        touchSupport: "wNLf",
        javaEnabled: "yD16",
        hasLiedBrowser: "2xC5",
        browserVersion: "d435",
        webSmartID: "E3gR",
        hasLiedLanguages: "j5po",
        systemLanguage: "e6OK",
        scrHeight: "5Jwy",
        appMinorVersion: "qBVW",
        openDatabase: "V8vl",
        scrDeviceXDPI: "3jCe",
        mimeTypes: "jp76",
        storeDb: "Fvje",
        doNotTrack: "VEek",
        localStorage: "XM7l",
        cpuClass: "Md7A",
        plugins: "ks0Q",
        userAgent: "0aew",
        timeZone: "q5aJ",
        cookieEnabled: "VPIf",
        cookieCode: "VySQ",
        adblock: "FMQw",
        sessionStorage: "HVia",
        appcodeName: "qT7b",
        hasLiedOs: "ci5c",
        browserName: "-UVA",
        jsFonts: "EOQP",
        os: "hAqN",
        scrColorDepth: "qmyu",
        historyList: "kU5z",
        srcScreenSize: "tOHY"
    };
    try {
        var t = r, w = t.document, ib = t.Image, Ma = t.globalStorage, jb = t.swfobject;
        try {
            var Na = t.localStorage
        } catch (a) {
        }
        try {
            var Oa = t.sessionStorage
        } catch (a) {
        }
        var ia, xa, kb = {
            pngPath: "/evercookie_png.php",
            baseurl: "",
            etagPath: "/evercookie_etag.php",
            cacheCookieName: "evercookie_cache",
            history: !1,
            java: !1,
            etagCookieName: "evercookie_etag",
            asseturi: "/assets",
            silverlight: !1,
            domain: Ga(t.location.host.split(":")[0]),
            authPath: !1,
            tests: 2,
            pngCookieName: "evercookie_png",
            phpuri: "/php",
            cachePath: "/evercookie_cache.php"
        };
        t._evercookie_flash_var = function (a) {
            ia = a;
            (a = w.getElementById("myswf")) && a.parentNode && a.parentNode.removeChild(a)
        };
        t.evercookie = t.Evercookie = function (a) {
            a = a || {};
            var b = {}, c;
            for (c in kb) {
                var d = a[c];
                b[c] = "undefined" !== typeof d ? d : kb[c]
            }
            "function" === typeof b.domain && (b.domain = b.domain(t));
            var e = b.history, f = b.java, h = b.tests, g = b.baseurl, k = b.asseturi,
                n = b.phpuri, q = b.domain, l = this;
            this._ec = {};
            this.get = function (a, b, c) {
                l._evercookie(a, b, void 0, void 0, c)
            };
            this.set = function (a, b) {
                l._evercookie(a, function () {
                }, b)
            };
            this._evercookie = function (a, c, d, g, k) {
                void 0 === l._evercookie && (l = this);
                void 0 === g && (g = 0);
                0 === g && (l.evercookie_database_storage(a, d), l.evercookie_indexdb_storage(a, d), b.authPath && l.evercookie_auth(a, d), f && l.evercookie_java(a, d), l._ec.userData = l.evercookie_userdata(a, d), l._ec.cookieData = l.evercookie_cookie(a, d), l._ec.localData = l.evercookie_local_storage(a,
                    d), l._ec.globalData = l.evercookie_global_storage(a, d), l._ec.sessionData = l.evercookie_session_storage(a, d), l._ec.windowData = l.evercookie_window(a, d), e && (l._ec.historyData = l.evercookie_history(a, d)));
                if (void 0 !== d) ("undefined" === typeof ia || "undefined" === typeof xa) && g++ < h && G(function () {
                    l._evercookie(a, c, d, g, k)
                }, 300); else if ((t.openDatabase && "undefined" === typeof l._ec.dbData || ("indexedDB" in t || (t.indexedDB = t.indexedDB || t.mozIndexedDB || t.webkitIndexedDB || t.msIndexedDB)) && ("undefined" === typeof l._ec.idbData ||
                        "" === l._ec.idbData) || "undefined" === typeof ia || "undefined" === typeof l._ec.etagData || "undefined" === typeof l._ec.cacheData || "undefined" === typeof l._ec.javaData || w.createElement("canvas").getContext && ("undefined" === typeof l._ec.pngData || "" === l._ec.pngData) || "undefined" === typeof xa) && g++ < h) G(function () {
                    l._evercookie(a, c, d, g, k)
                }, 20); else {
                    l._ec.lsoData = l.getFromStr(a, ia);
                    ia = void 0;
                    l._ec.slData = l.getFromStr(a, xa);
                    xa = void 0;
                    var u = l._ec, y = [], E = 0, A, m;
                    l._ec = {};
                    for (m in u) u[m] && "null" !== u[m] && "undefined" !== u[m] &&
                    (y[u[m]] = void 0 === y[u[m]] ? 1 : y[u[m]] + 1);
                    for (m in y) y[m] > E && (E = y[m], A = m);
                    void 0 === A || void 0 !== k && 1 === k || l.set(a, A);
                    "function" === typeof c && c(A, u)
                }
            };
            this.evercookie_window = function (a, b) {
                try {
                    if (void 0 !== b) {
                        var c;
                        var d = t.name;
                        if (-1 < d.indexOf("\x26" + a + "\x3d") || 0 === d.indexOf(a + "\x3d")) {
                            var e = d.indexOf("\x26" + a + "\x3d"), f;
                            -1 === e && (e = d.indexOf(a + "\x3d"));
                            f = d.indexOf("\x26", e + 1);
                            c = -1 !== f ? d.substr(0, e) + d.substr(f + (e ? 0 : 1)) + "\x26" + a + "\x3d" + b : d.substr(0, e) + "\x26" + a + "\x3d" + b
                        } else c = d + "\x26" + a + "\x3d" + b;
                        t.name = c
                    } else return this.getFromStr(a,
                        t.name)
                } catch (Jb) {
                }
            };
            this.evercookie_userdata = function (a, b) {
                try {
                    var c = this.createElem("div", "userdata_el", 1);
                    if (c.addBehavior) if (c.style.behavior = "url(#default#userData)", void 0 !== b) c.setAttribute(a, b), c.save(a); else return c.load(a), c.getAttribute(a)
                } catch (E) {
                }
            };
            this.evercookie_cache = function (a, c) {
                if (void 0 !== c) w.cookie = b.cacheCookieName + "\x3d" + c + "; path\x3d/; domain\x3d" + q, l.ajax({
                    success: function () {
                    }, url: g + n + b.cachePath + "?name\x3d" + a + "\x26cookie\x3d" + b.cacheCookieName
                }); else {
                    var d = this.getFromStr(b.cacheCookieName,
                        w.cookie);
                    l._ec.cacheData = void 0;
                    w.cookie = b.cacheCookieName + "\x3d; expires\x3dMon, 20 Sep 2010 00:00:00 UTC; path\x3d/; domain\x3d" + q;
                    l.ajax({
                        success: function (a) {
                            w.cookie = b.cacheCookieName + "\x3d" + d + "; expires\x3dTue, 31 Dec 2030 00:00:00 UTC; path\x3d/; domain\x3d" + q;
                            l._ec.cacheData = a
                        }, url: g + n + b.cachePath + "?name\x3d" + a + "\x26cookie\x3d" + b.cacheCookieName
                    })
                }
            };
            this.evercookie_auth = function (a, c) {
                if (void 0 !== c) {
                    var d = "//" + c + "@" + tb.host + g + n + b.authPath + "?name\x3d" + a, e = new ib;
                    e.style.visibility = "hidden";
                    e.style.position = "absolute";
                    e.src = d
                } else l.ajax({
                    url: g + n + b.authPath + "?name\x3d" + a, success: function (a) {
                        l._ec.authData = a
                    }
                })
            };
            this.evercookie_etag = function (a, c) {
                if (void 0 !== c) w.cookie = b.etagCookieName + "\x3d" + c + "; path\x3d/; domain\x3d" + q, l.ajax({
                    url: g + n + b.etagPath + "?name\x3d" + a + "\x26cookie\x3d" + b.etagCookieName,
                    success: function () {
                    }
                }); else {
                    var d = this.getFromStr(b.etagCookieName, w.cookie);
                    l._ec.etagData = void 0;
                    w.cookie = b.etagCookieName + "\x3d; expires\x3dMon, 20 Sep 2010 00:00:00 UTC; path\x3d/; domain\x3d" +
                        q;
                    l.ajax({
                        url: g + n + b.etagPath + "?name\x3d" + a + "\x26cookie\x3d" + b.etagCookieName,
                        success: function (a) {
                            w.cookie = b.etagCookieName + "\x3d" + d + "; expires\x3dTue, 31 Dec 2030 00:00:00 UTC; path\x3d/; domain\x3d" + q;
                            l._ec.etagData = a
                        }
                    })
                }
            };
            this.evercookie_java = function (a, b) {
                function c(c) {
                    c = w.getElementById(c);
                    void 0 !== b ? c.set(a, b) : l._ec.javaData = c.get(a)
                }

                var d = w.getElementById("ecAppletContainer");
                "undefined" !== typeof dtjava && (null !== d && void 0 !== d && d.length || (d = w.createElement("div"), d.setAttribute("id", "ecAppletContainer"),
                    d.style.position = "absolute", d.style.top = "-3000px", d.style.left = "-3000px", d.style.width = "1px", d.style.height = "1px", w.body.appendChild(d)), "undefined" === typeof ecApplet ? dtjava.embed({
                    width: "1px",
                    placeholder: "ecAppletContainer",
                    height: "1px",
                    id: "ecApplet",
                    url: g + k + "/evercookie.jnlp"
                }, {}, {onJavascriptReady: c}) : c("ecApplet"))
            };
            this.evercookie_lso = function (a, b) {
                var c = w.getElementById("swfcontainer"), d = {}, e = {}, f = {};
                null !== c && void 0 !== c && c.length || (c = w.createElement("div"), c.setAttribute("id", "swfcontainer"),
                    w.body.appendChild(c));
                void 0 !== b && (d.everdata = a + "\x3d" + b);
                e.swliveconnect = "true";
                f.id = "myswf";
                f.name = "myswf";
                jb.embedSWF(g + k + "/evercookie.swf", "swfcontainer", "1", "1", "9.0.0", !1, d, e, f)
            };
            this.evercookie_png = function (a, c) {
                var d = w.createElement("canvas"), e, f, h;
                d.style.visibility = "hidden";
                d.style.position = "absolute";
                d.width = 200;
                d.height = 1;
                d && d.getContext && (e = new ib, e.style.visibility = "hidden", e.style.position = "absolute", void 0 !== c ? w.cookie = b.pngCookieName + "\x3d" + c + "; path\x3d/; domain\x3d" + q : (l._ec.pngData =
                    void 0, f = d.getContext("2d"), h = this.getFromStr(b.pngCookieName, w.cookie), w.cookie = b.pngCookieName + "\x3d; expires\x3dMon, 20 Sep 2010 00:00:00 UTC; path\x3d/; domain\x3d" + q, e.onload = function () {
                    w.cookie = b.pngCookieName + "\x3d" + h + "; expires\x3dTue, 31 Dec 2030 00:00:00 UTC; path\x3d/; domain\x3d" + q;
                    l._ec.pngData = "";
                    f.drawImage(e, 0, 0);
                    var a = f.getImageData(0, 0, 200, 1).data, c, d;
                    c = 0;
                    for (d = a.length; c < d && 0 !== a[c]; c += 4) {
                        l._ec.pngData += String.fromCharCode(a[c]);
                        if (0 === a[c + 1]) break;
                        l._ec.pngData += String.fromCharCode(a[c +
                        1]);
                        if (0 === a[c + 2]) break;
                        l._ec.pngData += String.fromCharCode(a[c + 2])
                    }
                }), e.src = g + n + b.pngPath + "?name\x3d" + a + "\x26cookie\x3d" + b.pngCookieName)
            };
            this.evercookie_local_storage = function (a, b) {
                try {
                    if (Na) if (void 0 !== b) Na.setItem(a, b); else return Na.getItem(a)
                } catch (y) {
                }
            };
            this.evercookie_database_storage = function (a, b) {
                try {
                    if (t.openDatabase) {
                        var c = t.openDatabase("sqlite_evercookie", "", "evercookie", 1048576);
                        void 0 !== b ? c.transaction(function (c) {
                            c.executeSql("CREATE TABLE IF NOT EXISTS cache(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, value TEXT NOT NULL, UNIQUE (name))",
                                [], function () {
                                }, function () {
                                });
                            c.executeSql("INSERT OR REPLACE INTO cache(name, value) VALUES(?, ?)", [a, b], function () {
                            }, function () {
                            })
                        }) : c.transaction(function (b) {
                            b.executeSql("SELECT value FROM cache WHERE name\x3d?", [a], function (a, b) {
                                l._ec.dbData = 1 <= b.rows.length ? b.rows.item(0).value : ""
                            }, function () {
                            })
                        })
                    }
                } catch (E) {
                }
            };
            this.evercookie_indexdb_storage = function (a, b) {
                try {
                    if ("indexedDB" in t || (indexedDB = t.indexedDB || t.mozIndexedDB || t.webkitIndexedDB || t.msIndexedDB, IDBTransaction = t.IDBTransaction || t.webkitIDBTransaction ||
                            t.msIDBTransaction, IDBKeyRange = t.IDBKeyRange || t.webkitIDBKeyRange || t.msIDBKeyRange), indexedDB) {
                        var c = indexedDB.open("idb_evercookie", 1);
                        c.onerror = function () {
                        };
                        c.onupgradeneeded = function (a) {
                            a.target.result.createObjectStore("evercookie", {keyPath: "name", unique: !1})
                        };
                        c.onsuccess = void 0 !== b ? function (c) {
                            c = c.target.result;
                            c.objectStoreNames.contains("evercookie") && c.transaction(["evercookie"], "readwrite").objectStore("evercookie").put({
                                name: a,
                                value: b
                            });
                            c.close()
                        } : function (b) {
                            b = b.target.result;
                            if (b.objectStoreNames.contains("evercookie")) {
                                var c =
                                    b.transaction(["evercookie"]).objectStore("evercookie").get(a);
                                c.onsuccess = function () {
                                    l._ec.idbData = void 0 === c.result ? void 0 : c.result.value
                                }
                            } else l._ec.idbData = void 0;
                            b.close()
                        }
                    }
                } catch (E) {
                }
            };
            this.evercookie_session_storage = function (a, b) {
                try {
                    if (Oa) if (void 0 !== b) Oa.setItem(a, b); else return Oa.getItem(a)
                } catch (y) {
                }
            };
            this.evercookie_global_storage = function (a, b) {
                if (Ma) {
                    var c = this.getHost();
                    try {
                        if (void 0 !== b) Ma[c][a] = b; else return Ma[c][a]
                    } catch (E) {
                    }
                }
            };
            this.encode = function (a) {
                var b = "", c, d, e, f, h, g, l = 0;
                for (a =
                         this._utf8_encode(a); l < a.length;) c = a.charCodeAt(l++), d = a.charCodeAt(l++), e = a.charCodeAt(l++), f = c >> 2, c = (c & 3) << 4 | d >> 4, h = (d & 15) << 2 | e >> 6, g = e & 63, isNaN(d) ? h = g = 64 : isNaN(e) && (g = 64), b = b + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".charAt(f) + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".charAt(c) + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".charAt(h) + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".charAt(g);
                return b
            };
            this.decode = function (a) {
                var b = "", c, d, e, f, h, g = 0;
                for (a = a.replace(/[^A-Za-z0-9\+\/\=]/g, ""); g < a.length;) c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".indexOf(a.charAt(g++)), d = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".indexOf(a.charAt(g++)), f = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".indexOf(a.charAt(g++)), h = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d".indexOf(a.charAt(g++)), c = c << 2 |
                    d >> 4, d = (d & 15) << 4 | f >> 2, e = (f & 3) << 6 | h, b += String.fromCharCode(c), 64 !== f && (b += String.fromCharCode(d)), 64 !== h && (b += String.fromCharCode(e));
                return b = this._utf8_decode(b)
            };
            this._utf8_encode = function (a) {
                a = a.replace(/\r\n/g, "\n");
                for (var b = "", c = 0, d = a.length, e; c < d; c++) e = a.charCodeAt(c), 128 > e ? b += String.fromCharCode(e) : (127 < e && 2048 > e ? b += String.fromCharCode(e >> 6 | 192) : (b += String.fromCharCode(e >> 12 | 224), b += String.fromCharCode(e >> 6 & 63 | 128)), b += String.fromCharCode(e & 63 | 128));
                return b
            };
            this._utf8_decode = function (a) {
                for (var b =
                    "", c = 0, d = a.length, e, f, h; c < d;) e = a.charCodeAt(c), 128 > e ? (b += String.fromCharCode(e), c += 1) : 191 < e && 224 > e ? (f = a.charCodeAt(c + 1), b += String.fromCharCode((e & 31) << 6 | f & 63), c += 2) : (f = a.charCodeAt(c + 1), h = a.charCodeAt(c + 2), b += String.fromCharCode((e & 15) << 12 | (f & 63) << 6 | h & 63), c += 3);
                return b
            };
            this.evercookie_history = function (a, b) {
                var c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\x3d-".split(""),
                    d = "" + this.getHost() + "/" + a, e, f = "", h = "", g = 1;
                if (void 0 !== b) {
                    if (!this.hasVisited(d)) {
                        this.createIframe(d, "if");
                        d += "/";
                        c = this.encode(b).split("");
                        for (e = 0; e < c.length; e++) d += c[e], this.createIframe(d, "if" + e);
                        this.createIframe(d + "-", "if_")
                    }
                } else if (this.hasVisited(d)) {
                    for (d += "/"; "-" !== f && 1 === g;) for (e = g = 0; e < c.length; e++) if (this.hasVisited(d + c[e])) {
                        f = c[e];
                        "-" !== f && (h += f);
                        d += f;
                        g = 1;
                        break
                    }
                    return this.decode(h)
                }
            };
            this.createElem = function (a, b, c) {
                a = void 0 !== b && w.getElementById(b) ? w.getElementById(b) : w.createElement(a);
                a.style.visibility = "hidden";
                a.style.position = "absolute";
                b && a.setAttribute("id", b);
                c && w.body.appendChild(a);
                return a
            };
            this.createIframe = function (a, b) {
                this.createElem("iframe", b, 1).setAttribute("src", a)
            };
            var p = this.waitForSwf = function (a) {
                void 0 === a ? a = 0 : a++;
                a < h && "undefined" === typeof jb && G(function () {
                    p(a)
                }, 300)
            };
            this.evercookie_cookie = function (a, b) {
                if (void 0 !== b) w.cookie = a + "\x3d" + b + "; expires\x3dTue, 31 Dec 2030 00:00:00 UTC; path\x3d/; domain\x3d" + q; else return this.getFromStr(a, w.cookie)
            };
            this.getFromStr = function (a, b) {
                if ("string" === typeof b) {
                    var c = a + "\x3d", d = b.split(/[;&]/), e, f;
                    for (e = 0; e < d.length; e++) {
                        for (f =
                                 d[e]; " " === f.charAt(0);) f = f.substring(1, f.length);
                        if (0 === f.indexOf(c)) return f.substring(c.length, f.length)
                    }
                }
            };
            this.getHost = function () {
                return Ga(t.location.host.split(":")[0])
            };
            this.toHex = function (a) {
                for (var b = "", c = a.length, d = 0, e; d < c;) {
                    for (e = a.charCodeAt(d++).toString(16); 2 > e.length;) e = "0" + e;
                    b += e
                }
                return b
            };
            this.fromHex = function (a) {
                for (var b = "", c = a.length, d; 0 <= c;) d = c - 2, b = String.fromCharCode("0x" + a.substring(d, c)) + b, c = d;
                return b
            };
            this.hasVisited = function (a) {
                -1 === this.no_color && -1 === this._getRGB("",
                    -1) && (this.no_color = this._getRGB("" + Math.floor(9999999 * Math.random()) + "rand.com"));
                return 0 === a.indexOf("https:") || 0 === a.indexOf("http:") ? this._testURL(a, this.no_color) : this._testURL("http://" + a, this.no_color) || this._testURL("https://" + a, this.no_color) || this._testURL("http://www." + a, this.no_color) || this._testURL("https://www." + a, this.no_color)
            };
            var r = this.createElem("a", "_ec_rgb_link"), v, x;
            try {
                v = 1, x = w.createElement("style"), x.styleSheet ? x.styleSheet.innerHTML = "#_ec_rgb_link:visited{display:none;color:#FF0000}" :
                    x.innerHTML ? x.innerHTML = "#_ec_rgb_link:visited{display:none;color:#FF0000}" : x.appendChild(w.createTextNode("#_ec_rgb_link:visited{display:none;color:#FF0000}"))
            } catch (u) {
                v = 0
            }
            this._getRGB = function (a, b) {
                if (b && 0 === v) return -1;
                r.href = a;
                r.innerHTML = a;
                w.body.appendChild(x);
                w.body.appendChild(r);
                var c;
                if (w.defaultView) {
                    if (null == w.defaultView.getComputedStyle(r, null)) return -1;
                    c = w.defaultView.getComputedStyle(r, null).getPropertyValue("color")
                } else c = r.currentStyle.color;
                return c
            };
            this._testURL = function (a, b) {
                var c =
                    this._getRGB(a);
                return "rgb(255, 0, 0)" === c || "#ff0000" === c || b && c !== b ? 1 : 0
            }
        }
    } catch (a) {
    }
    var lb = {
        postMessage: function (a, b) {
            b = b || ub;
            if (b.postMessage) b.postMessage(a, "*"); else if (a && "function" == typeof k.onData) k.onData(a)
        }
    };
    ja.prototype = {
        getOnLine: function () {
            return new n("onLine", k.onLine.toString())
        }, getScrAvailHeight: function () {
            return new n("scrAvailHeight", r.screen.availHeight.toString())
        }, md5ScrColorDepth: function () {
            return new n("scrColorDepth", r.screen.colorDepth.toString())
        }, getHistoryList: function () {
            return new n("historyList",
                r.history.length)
        }, initEc: function (a) {
            var b = "", c = this, d = void 0 != a && void 0 != a.localAddr ? a.localAddr : "";
            c.checkWapOrWeb();
            this.ec.get("RAIL_OkLJUJ", function (a) {
                b = a;
                c.getDfpMoreInfo(function () {
                    if (!(9E5 < F("RAIL_EXPIRATION") - (new Date).getTime() & null != F("RAIL_DEVICEID") & void 0 != F("RAIL_DEVICEID") & !c.NeedUpdate())) {
                        for (var a = "", e = "", g = c.getpackStr(b), k = [], q = [], t = [], l = [], p = 0; p < g.length; p++) "new" != g[p].value && -1 == Cb.indexOf(g[p].key) && (-1 != Ib.indexOf(g[p].key) ? q.push(g[p]) : -1 != Db.indexOf(g[p].key) ? t.push(g[p]) :
                            -1 != Bb.indexOf(g[p].key) ? l.push(g[p]) : k.push(g[p]));
                        g = "";
                        for (p = 0; p < q.length; p++) g = g + q[p].key.charAt(0) + q[p].value;
                        q = "";
                        for (p = 0; p < l.length; p++) q = 0 == p ? q + l[p].value : q + "x" + l[p].value;
                        l = "";
                        for (p = 0; p < t.length; p++) l = 0 == p ? l + t[p].value : l + "x" + t[p].value;
                        k.push(new n("storeDb", g));
                        k.push(new n("srcScreenSize", q));
                        k.push(new n("scrAvailSize", l));
                        "" != d && k.push(new n("localCode", qb(d)));
                        e = c.hashAlg(k, a, e);
                        a = e.key;
                        e = e.value;
                        a += "\x26timestamp\x3d" + (new Date).getTime();
                        $a.getJSON("/otn/HttpZF/logdevice" + ("?algID\x3d0L4f8CC5Qd\x26hashCode\x3d" +
                            e + a), null, function (a) {
                            var b = JSON.parse(a);
                            void 0 != lb && lb.postMessage(a, r.parent);
                            for (var d in b) "dfp" == d ? F("RAIL_DEVICEID") != b[d] && (W("RAIL_DEVICEID", b[d], 1E3), c.deviceEc.set("RAIL_DEVICEID", b[d])) : "exp" == d ? W("RAIL_EXPIRATION", b[d], 1E3) : "cookieCode" == d && (c.ec.set("RAIL_OkLJUJ", b[d]), W("RAIL_OkLJUJ", "", 0))
                        })
                    }
                })
            }, 1)
        }, getCookieEnabled: function () {
            return new n("cookieEnabled", k.cookieEnabled ? "1" : "0")
        }, getScrAvailWidth: function () {
            return new n("scrAvailWidth", r.screen.availWidth.toString())
        }, getSystemLanguage: function () {
            var a;
            a = "IE" == this.checkBroswer() || this.checkOperaBroswer() ? k.systemLanguage.toString() : "";
            return new n("systemLanguage", a)
        }, getIndexedDb: function (a) {
            return new n("indexedDb", a)
        }, getSessionStorage: function (a) {
            return new n("sessionStorage", a)
        }, checkBroswer: function () {
            k.userAgent.toString().indexOf("MSIE")
        }, checkOperaBroswer: function () {
            return r.opera
        }, getCanvansCode: function (a) {
            var b;
            b = this.checkWapOrWeb() ? "wapSmartID" : "webSmartID";
            return new n(b, a)
        }, getBrowserLanguage: function () {
            var a;
            a = "IE" == this.checkBroswer() ||
            this.checkOperaBroswer() ? k.browserLanguage.toString() : this.getLanguage();
            return new n("browserLanguage", a)
        }, hashAlg: function (a, b, c) {
            a.sort(function (a, b) {
                var c, d;
                if ("object" === typeof a && "object" === typeof b && a && b) return c = a.key, d = b.key, c === d ? 0 : typeof c === typeof d ? c < d ? -1 : 1 : typeof c < typeof d ? -1 : 1;
                throw"error";
            });
            for (var d = 0; d < a.length; d++) {
                var e = a[d].key.replace(RegExp("%", "gm"), ""), f = "",
                    f = "string" == typeof a[d].value ? a[d].value.replace(RegExp("%", "gm"), "") : a[d].value;
                "" !== f && (c += e + f, b += "\x26" + (void 0 ==
                hb[e] ? e : hb[e]) + "\x3d" + f)
            }
            a = c;
            c = a.length;
            d = 0 == c % 3 ? parseInt(c / 3) : parseInt(c / 3) + 1;
            3 > c || (e = a.substring(0, 1 * d), f = a.substring(1 * d, 2 * d), a = a.substring(2 * d, c) + e + f);
            a = R.SHA256(a).toString(R.enc.Base64);
            a = Qa(a);
            d = Qa(a);
            e = d.length;
            f = 0 == e % 3 ? parseInt(e / 3) : parseInt(e / 3) + 1;
            3 > e ? c = d : (a = d.substring(0, 1 * f), c = d.substring(1 * f, 2 * f), d = d.substring(2 * f, e), c = c + d + a);
            c = R.SHA256(c).toString(R.enc.Base64);
            return new n(b, c)
        }, getAppMinorVersion: function () {
            var a;
            a = "IE" == this.checkBroswer() ? k.appMinorVersion.toString() : "";
            return new n("appMinorVersion",
                a)
        }, getScrDeviceXDPI: function () {
            var a;
            a = "IE" == this.checkBroswer() ? r.screen.deviceXDPI.toString() : "";
            return new n("scrDeviceXDPI", a)
        }, getScrWidth: function () {
            return new n("scrWidth", r.screen.width.toString())
        }, getCookieCode: function () {
            "" == F("RAIL_OkLJUJ") || null == F("RAIL_OkLJUJ") || void 0 == F("RAIL_OkLJUJ") || F("RAIL_OkLJUJ");
            return new n("cookieCode", "new")
        }, NeedUpdate: function () {
            W("fp_ver", "4.6.1", 0);
            W("BSFIT_OKLJUJ", "", 0);
            return !1
        }, getDoNotTrack: function (a) {
            return new n("doNotTrack", a)
        }, getCpuClass: function () {
            var a;
            a = "IE" == this.checkBroswer() ? k.cpuClass.toString() : "";
            return new n("cpuClass", a)
        }, getAppName: function () {
            return new n("appName", k.appName.toString())
        }, getHasLiedLanguages: function (a) {
            return new n("hasLiedLanguages", a)
        }, getLocalStorage: function (a) {
            return new n("localStorage", a)
        }, getHasLiedResolution: function (a) {
            return new n("hasLiedResolution", a)
        }, getUserLanguage: function () {
            var a;
            a = "IE" == this.checkBroswer() || this.checkOperaBroswer() ? k.userLanguage.toString() : "";
            return new n("userLanguage", a)
        }, getScrHeight: function () {
            return new n("scrHeight",
                r.screen.height.toString())
        }, getHasLiedOs: function (a) {
            return new n("hasLiedOs", a)
        }, getUserAgent: function () {
            var a = k.userAgent, a = a.replace(/\&|\+/g, "");
            return new n("userAgent", a.toString())
        }, getPlugins: function (a) {
            if ("IE" == this.checkBroswer()) return new n("plugins", ba(a.replace(RegExp(",", "gm"), "#")));
            a = k.plugins;
            var b = "";
            for (i = 0; i < a.length; i++) b += a[i].name.toString() + "#";
            return new n("plugins", ba(b))
        }, getPlatform: function () {
            return new n("os", k.platform.toString())
        }, getMimeTypes: function () {
            for (var a =
                k.mimeTypes, b = "", c = 0; c < a.length; c++) b += a[c].type + "#";
            return new n("mimeTypes", ba(b.substr(0, b.length - 1)))
        }, getTimeZone: function () {
            var a = (new Date).getTimezoneOffset() / 60;
            return new n("timeZone", a)
        }, getpackStr: function (a) {
            var b = [], b = [], b = this.getMachineCode(), b = b.concat(this.moreInfoArray);
            null != a && void 0 != a && "" != a && 32 == a.length && b.push(new n("cookieCode", a));
            b.sort(function (a, b) {
                var c, d;
                if ("object" === typeof a && "object" === typeof b && a && b) return c = a.key, d = b.key, c === d ? 0 : typeof c === typeof d ? c < d ? -1 : 1 : typeof c <
                typeof d ? -1 : 1;
                throw"error";
            });
            return b
        }, getLanguage: function () {
            return null != k.language ? k.language.toString() : ""
        }, getDfpMoreInfo: function (a) {
            var b = this;
            this.moreInfoArray = [];
            b.cfp.get(function (c, d) {
                b.moreInfoArray.push(b.getCanvansCode(c + ""));
                for (var e in d) {
                    c = d[e].key;
                    var f = d[e].value + "";
                    switch (c) {
                        case "session_storage":
                            b.moreInfoArray.push(b.getSessionStorage(f));
                            break;
                        case "local_storage":
                            b.moreInfoArray.push(b.getLocalStorage(f));
                            break;
                        case "indexed_db":
                            b.moreInfoArray.push(b.getIndexedDb(f));
                            break;
                        case "open_database":
                            b.moreInfoArray.push(b.getOpenDatabase(f));
                            break;
                        case "do_not_track":
                            b.moreInfoArray.push(b.getDoNotTrack(f));
                            break;
                        case "ie_plugins":
                            b.moreInfoArray.push(b.getPlugins(f));
                            break;
                        case "regular_plugins":
                            b.moreInfoArray.push(b.getPlugins());
                            break;
                        case "adblock":
                            b.moreInfoArray.push(b.getAdblock(f));
                            break;
                        case "has_lied_languages":
                            b.moreInfoArray.push(b.getHasLiedLanguages(f));
                            break;
                        case "has_lied_resolution":
                            b.moreInfoArray.push(b.getHasLiedResolution(f));
                            break;
                        case "has_lied_os":
                            b.moreInfoArray.push(b.getHasLiedOs(f));
                            break;
                        case "has_lied_browser":
                            b.moreInfoArray.push(b.getHasLiedBrowser(f));
                            break;
                        case "touch_support":
                            b.moreInfoArray.push(b.getTouchSupport(f));
                            break;
                        case "js_fonts":
                            b.moreInfoArray.push(b.getJsFonts(f))
                    }
                }
                "function" == typeof a && a()
            })
        }, checkWapOrWeb: function () {
            return "WindowsPhone" == Ha() || "iOS" == Ha() || "Android" == Ha() ? !0 : !1
        }, getFingerPrint: function () {
            var a = this;
            r.RTCPeerConnection || r.webkitRTCPeerConnection || r.mozRTCPeerConnection ? nb(function (b) {
                a.initEc(b)
            }) : a.initEc()
        }, getHasLiedBrowser: function (a) {
            return new n("hasLiedBrowser",
                a)
        }, getJavaEnabled: function () {
            return new n("javaEnabled", k.javaEnabled() ? "1" : "0")
        }, getAppCodeName: function () {
            return new n("appCodeName", k.appCodeName.toString())
        }, getJsFonts: function (a) {
            return new n("jsFonts", ba(a.replace(RegExp(",", "gm"), "#")))
        }, getSendPlatform: function () {
            var a;
            a = this.checkWapOrWeb() ? Za[1] : Za[0];
            return new n("platform", a)
        }, getOpenDatabase: function (a) {
            return new n("openDatabase", a)
        }, getFlashVersion: function () {
            var a = 0;
            if ("IE" == this.checkBroswer()) var b = new ActiveXObject("ShockwaveFlash.ShockwaveFlash"),
                a = Number(b.GetVariable("$version").split(" ")[1].replace(/,/g, ".").replace(/^(d+.d+).*$/, "$1")); else k.plugins && 0 < k.plugins.length && (b = k.plugins["Shockwave Flash"]) && (flashArr = b.description.split(" "), a = flashArr[2] + " " + flashArr[3]);
            return new n("flashVersion", a)
        }, getUUID: function () {
            return "" == F("RAIL_UUID") || null == F("RAIL_UUID") || void 0 == F("RAIL_UUID") ? new n("cookieCode", "new") : new n("UUID", F("RAIL_UUID"))
        }, getTouchSupport: function (a) {
            return new n("touchSupport", ba(a.replace(RegExp(",", "gm"), "#")))
        },
        getAdblock: function (a) {
            return new n("adblock", a)
        }, getMachineCode: function () {
            return [this.getUUID(), this.getCookieCode(), this.getUserAgent(), this.getScrHeight(), this.getScrWidth(), this.getScrAvailHeight(), this.getScrAvailWidth(), this.md5ScrColorDepth(), this.getScrDeviceXDPI(), this.getAppCodeName(), this.getAppName(), this.getJavaEnabled(), this.getMimeTypes(), this.getPlatform(), this.getAppMinorVersion(), this.getBrowserLanguage(), this.getCookieEnabled(), this.getCpuClass(), this.getOnLine(), this.getSystemLanguage(),
                this.getUserLanguage(), this.getTimeZone(), this.getFlashVersion(), this.getHistoryList(), this.getCustId(), this.getSendPlatform()]
        }, getCustId: function () {
            return new n("custID", "133")
        }
    };
    var mb = !1;
    v.addEventListener ? v.addEventListener("DOMContentLoaded", function b() {
        v.removeEventListener("DOMContentLoaded", b, !1);
        Pa()
    }, !1) : v.attachEvent && v.attachEvent("onreadystatechange", function c() {
        mb || "interactive" != v.readyState && "complete" != v.readyState || (v.detachEvent("onreadystatechange", c), Pa(), mb = !0)
    })
})();