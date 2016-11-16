#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.${artifactId};

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ${package}.util.Constants;
import ${package}.util.cache.MC;
import ${package}.util.hibernate.HibernateUtil;
import ${package}.util.hibernate.TableIDCreator;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/${artifactId}")
public class PayController implements IPay {
    public Logger logger = LoggerFactory.getLogger(PayController.class);

    @Override
    public long generateOrder(Pay ${artifactId}) {
        // 生成订单
        long billno = TableIDCreator.getTableID(Pay.class, 1);
        // logger.info("generate order billno:{}", billno);
        ${artifactId}.setBillno(billno);
        ${artifactId}.setPayDate(new Date());
        ${artifactId}.setIsFinished(PayCode.NOT_VALIDATE);
        MC.add(${artifactId}, "" + ${artifactId}.getIdentifier());
        HibernateUtil.insert(${artifactId}, ${artifactId}.getUserid());
        // 发回订单号billno
        return billno;
    }

    @Override
    public boolean sendGoods(long billno, boolean success) {
        if (success) {
            Pay ${artifactId} = HibernateUtil.find(Pay.class, billno);
            ${artifactId}.setIsFinished(PayCode.FINISH);// 支付成功，发货成功
            HibernateUtil.save(${artifactId}, billno);
            // MemcachedCRUD.getInstance().deleteObject(billno + "");
        }
        return true;
    }

    @Override
    public int queryPaySum(long userid) {
        List<Pay> ${artifactId}List = HibernateUtil.list(Pay.class, "where userid='"
                + userid + "'");
        int sum = 0;
        for (Pay ${artifactId} : ${artifactId}List) {
            if (${artifactId}.getIsFinished() == PayCode.FINISH) {
                sum += ${artifactId}.getAmount();
            }
        }
        return sum;
    }

    /**
     * 查询订单接口
     **/
    @Override
    public Pay queryOrder(long billno) {
        Pay ${artifactId} = HibernateUtil
                .find(Pay.class, "where billno='" + billno + "'");
        return ${artifactId};
    }

    @RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
    @ResponseBody
    public String queryOrder(HttpServletRequest request) {
        // logger.info("查询订单，{}", JSON.toJSONString(request.getParameterMap()));
        Pay ${artifactId} = HibernateUtil.find(Pay.class,
                "where billno=" + request.getParameter("billno") + "");
        String ret = ${artifactId} == null ? "" : JSON.toJSONString(${artifactId});
        return ret;
    }

    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public String generateOrder(HttpServletRequest request) {
        logger.info("生成订单:{}", JSON.toJSONString(request.getParameterMap()));
        // 生成订单
        long billno = TableIDCreator.getTableID(Pay.class, 1);
        Pay ${artifactId} = new Pay();
        ${artifactId}.setUserid(Integer.parseInt(request.getParameter("userid")));
        ${artifactId}.setZone((int) (${artifactId}.getUserid() % 1000));
        ${artifactId}.setGoodType(Integer.parseInt(request.getParameter("goodType")));
        ${artifactId}.setGamename(request.getParameter("gamename"));
        ${artifactId}.setChannel(request.getParameter("channel"));
        ${artifactId}.setBillno(billno);
        ${artifactId}.setAmount(Double.parseDouble(request.getParameter("amount")));
        ${artifactId}.setPayDate(new Date());
        ${artifactId}.setIsFinished(PayCode.NOT_VALIDATE);
        MC.add(${artifactId}, "" + ${artifactId}.getIdentifier());
        HibernateUtil.insert(${artifactId}, ${artifactId}.getUserid());
        // 发回订单号billno
        return billno + "";
    }

    /***
     * 以下方法为接第三方联运渠道SDK的支付回调地址
     */

    @RequestMapping(value = "/xyPayHandle", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public String xyPayHandle(HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        logger.info("xy支付回调,{}", JSON.toJSONString(request.getParameterMap()));
//		String uid = request.getParameter("uid");
//		String orderid = request.getParameter("orderid");
//		String serverid = request.getParameter("serverid");
//		String amount = request.getParameter("amount");
//		String extra = request.getParameter("extra");
//		String ts = request.getParameter("ts");
//		String sign = request.getParameter("sign");
//		String sig = request.getParameter("sig");
//		long billno = Long.parseLong(extra);
//		// 修改订单状态
//		Pay ${artifactId} = HibernateUtil.find(Pay.class, billno);
//		if (${artifactId} == null) {
//			ret.put("ret", 5);
//			ret.put("msg", "订单不存在");
//			return write(ret);
//		}
//		boolean flag = true;
//		// 验签
//		Map maps = new TreeMap();
//		maps.put("uid", uid);
//		maps.put("orderid", orderid);
//		maps.put("serverid", serverid);
//		maps.put("amount", amount);
//		maps.put("extra", extra);
//		maps.put("ts", ts);
//		// 验证App签名串
//		String genSafeSign = GenSafeSign.getGenSafeSign(maps, PayKeys.xyAppKey);
//		if (!genSafeSign.equalsIgnoreCase(sign)) {
//			flag = false;
//		}
//		// 如果支付签名串存在就验证
//		else if (!sig.isEmpty()) {
//			genSafeSign = GenSafeSign.getGenSafeSign(maps, PayKeys.xyPayKey);
//			if (!genSafeSign.equalsIgnoreCase(sig)) {
//				flag = false;
//			}
//		}
//		if (!flag) {
//			${artifactId}.setIsFinished(PayCode.VALIDATE_ERR);// 验证失败
//			HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//			ret.put("ret", 6);
//			ret.put("msg", "君主" + ${artifactId}.getUserid() + "支付验证失败");
//			return write(ret);
//		}
//		${artifactId}.setAccount(uid);
//		${artifactId}.setIsFinished(PayCode.NOT_SEND);// 支付成功
//		long serverId = ${artifactId}.getUserid() % 1000;
//		String serverRet = HttpClient.get("http://"
//				+ GameInit.cfg.get("loginServer") + ":"
//				+ GameInit.cfg.get("loginPort", 80) + "/"
//				+ GameInit.cfg.get("loginName")
//				+ "/route/getServerConfig?serverid=" + serverId + "");
//		boolean serverFlag = false;
//		if (serverRet != null) {
//			JSONArray serverArr = JSONArray.parseArray(serverRet);
//			try {
//				JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(
//						"http://" + serverArr.getString(0) + ":"
//								+ serverArr.getIntValue(1) + "/36/ShopRpc"));
//				serverFlag = client.invoke("sendGoods",
//						new String[] { ${artifactId}.getGoodType() + "",
//								${artifactId}.getUserid() + "" }, Boolean.class);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (Throwable e) {
//				e.printStackTrace();
//			}
//		}
//		if (serverFlag) {
//			${artifactId}.setIsFinished(PayCode.FINISH);
//			HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//			ret.put("ret", 0);
//			ret.put("msg", "发货成功");
//			return write(ret);
//		} else {
//			${artifactId}.setIsFinished(PayCode.SEND_ERR);// 发货失败
//			HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//			ret.put("ret", 8);
//			ret.put("msg", "发货失败");
			return write(ret);
//		}
    }

    @RequestMapping(value = "/xiaomiPayHandle", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public String xiaomiPayHandle(HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        logger.info("小米支付回调,{}", JSON.toJSONString(request.getParameterMap()));
//		String queryString = request.getQueryString();
//		JSONObject obj = new JSONObject();
//		int resultCode = Constant.CODE_SUCCESS;
//		try {
//			Map<String, String> signParams = XiaoMiService
//					.getSignParams(queryString);
//			// 修改订单状态
//			Pay ${artifactId} = HibernateUtil.find(Pay.class,
//					Long.parseLong(signParams.get("cpOrderId")));
//			if (${artifactId} == null) {
//				ret.put("errcode", Constant.CODE_ORDER_INVAIL);
//				ret.put("errMsg", "订单不存在");
//				return write(ret);
//			}
//			String tmpSign = XiaoMiService.getSign(signParams);
//			String sign = request.getParameter("signature");
//			if (tmpSign.equals(sign)) {
//				signParams.put("signature", sign);
//				if (!signParams.get("orderStatus").equals("TRADE_SUCCESS")) {
//					ret.put("errcode", Constant.CODE_ORDER_INVAIL);
//					ret.put("errMsg", signParams.get("orderStatus"));
//					return write(ret);
//				}
//				${artifactId}.setAccount(signParams.get("uid"));
//				${artifactId}.setIsFinished(PayCode.NOT_SEND);// 支付成功
//				long serverId = ${artifactId}.getUserid() % 1000;
//				String serverRet = HttpClient.get("http://"
//						+ GameInit.cfg.get("loginServer") + ":"
//						+ GameInit.cfg.get("loginPort", 80) + "/"
//						+ GameInit.cfg.get("loginName")
//						+ "/route/getServerConfig?serverid=" + serverId + "");
//				boolean serverFlag = false;
//				if (serverRet != null) {
//					JSONArray serverArr = JSONArray.parseArray(serverRet);
//					try {
//						JsonRpcHttpClient client = new JsonRpcHttpClient(
//								new URL("http://" + serverArr.getString(0)
//										+ ":" + serverArr.getIntValue(1)
//										+ "/36/ShopRpc"));
//						serverFlag = client.invoke("sendGoods", new String[] {
//								${artifactId}.getGoodType() + "", ${artifactId}.getUserid() + "" },
//								Boolean.class);
//					} catch (MalformedURLException e) {
//						e.printStackTrace();
//					} catch (Throwable e) {
//						e.printStackTrace();
//					}
//				}
//				if (serverFlag) {
//					${artifactId}.setIsFinished(PayCode.FINISH);
//					HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//					ret.put("errcode", Constant.CODE_SUCCESS);
//					ret.put("errMsg", "发货成功");
//					return write(ret);
//				} else {
//					${artifactId}.setIsFinished(PayCode.SEND_ERR);// 发货失败
//					HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//					ret.put("errcode", Constant.CODE_SYS_ERROR);
//					ret.put("errMsg", "发货失败");
//					return write(ret);
//				}
//			} else {
//				resultCode = Constant.CODE_SIGN_ERROR;
//				${artifactId}.setIsFinished(PayCode.VALIDATE_ERR);// 验签失败
//				HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//				ret.put("errcode", resultCode);
//				ret.put("errMsg", "君主" + ${artifactId}.getUserid() + "支付验签失败");
//				return write(ret);
//			}
//		} catch (Exception e) {
//			resultCode = Constant.CODE_SYS_ERROR;
//			e.printStackTrace();
//			obj.put("errcode", resultCode);
			return write(ret);
//		}
    }

    @RequestMapping(value = "/jifengPayHandle", method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public String jifengPayHandle(HttpServletRequest request)
            throws DocumentException {
        JSONObject ret = new JSONObject();
//		logger.info("机锋支付回调,{}", JSON.toJSONString(request.getParameterMap()));
//		long billno = Long.parseLong(request.getParameter("billno"));
//		// 单订单查询接口
//		StringBuffer orderReq = new StringBuffer();
//		orderReq.append("<request>");
//		orderReq.append("<order_id>" + billno + "</order_id>");
//		orderReq.append("<app_key>" + JifengUtil.APP_KEY + "</app_key>");
//		orderReq.append("</request>");
//		String urlStr = "http://api.gfan.com/sdk/${artifactId}/queryAppPayLog";
//		String orderResp = JifengUtil.doEncryptRequest(orderReq.toString(),
//				urlStr);
//		// 字符串转XML
//		Document document = DocumentHelper.parseText(orderResp);
//		int orderState = Integer.parseInt(((Node) document.node(0)
//				.selectObject("result")).getText());
//		boolean flag = orderState == 1 ? true : false;
//		Pay ${artifactId} = HibernateUtil.find(Pay.class, billno);
//		if (${artifactId} == null) {
//			ret.put("ret", PayCode.NOT_VALIDATE);
//			ret.put("msg", "订单不存在");
//			return write(ret);
//		}
//		if (!flag) {
//			${artifactId}.setIsFinished(PayCode.VALIDATE_ERR);// 验证失败
//			HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//			ret.put("ret", PayCode.VALIDATE_ERR);
//			ret.put("msg", "君主" + ${artifactId}.getUserid() + "支付验证失败");
//			return write(ret);
//		}
//		${artifactId}.setIsFinished(PayCode.NOT_SEND);// 支付成功
//		long serverId = ${artifactId}.getUserid() % 1000;
//		String serverRet = HttpClient.get("http://"
//				+ GameInit.cfg.get("loginServer") + ":"
//				+ GameInit.cfg.get("loginPort", 80) + "/"
//				+ GameInit.cfg.get("loginName")
//				+ "/route/getServerConfig?serverid=" + serverId + "");
//		boolean serverFlag = false;
//		if (serverRet != null) {
//			JSONArray serverArr = JSONArray.parseArray(serverRet);
//			try {
//				JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(
//						"http://" + serverArr.getString(0) + ":"
//								+ serverArr.getIntValue(1) + "/36/ShopRpc"));
//				serverFlag = client.invoke("sendGoods",
//						new String[] { ${artifactId}.getGoodType() + "",
//								${artifactId}.getUserid() + "" }, Boolean.class);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (Throwable e) {
//				e.printStackTrace();
//			}
//		}
//		if (serverFlag) {
//			${artifactId}.setIsFinished(PayCode.FINISH);
//			HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//			ret.put("ret", PayCode.FINISH);
//			ret.put("msg", "发货成功");
//			return write(ret);
//		} else {
//			${artifactId}.setIsFinished(PayCode.SEND_ERR);// 发货失败
//			HibernateUtil.save(${artifactId}, ${artifactId}.getUserid());
//			ret.put("ret", PayCode.SEND_ERR);
//			ret.put("msg", "发货失败");
			return write(ret);
//		}
    }

    protected String write(Object msg) {
        String result = (msg instanceof String) ? (String) msg : JSON
                .toJSONString(msg);
        if (Constants.CLIENT_DEBUG) {
            logger.info("write:{}", result);
        }
        return result;
    }
}
