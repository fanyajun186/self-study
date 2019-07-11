package com.example.demo.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class HttpUtil {

	private static final int defaultConnectTime = 10000;

	private static final int defaultSocketTime = 80000;

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 *
	 * @param url
	 * @param param
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Object param, Map<String, String> header) throws Exception {
		return post(url, param, defaultConnectTime, defaultSocketTime, header);
	}

	/**
	 * post调用
	 * @param url  请求地址
	 * @param param 参数
	 */
	public static String post(String url, Object param, int connectTime, int socketTime, Map<String, String> header) throws Exception {
		return post(url, param, connectTime, socketTime, header, true);
	}

	/**
	 *
	 * @param url
	 * @param param
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Object param, int connectTime, int socketTime, Map<String, String> header, boolean printLog) throws Exception {
		return post(url, param, connectTime, socketTime, header, printLog, false);
	}

	/**
	 *
	 * @param url
	 * @param param
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String postWithOutRetry(String url, Object param, Map<String, String> header) throws Exception {
		return postWithOutRetry(url, param, defaultConnectTime, defaultSocketTime, header);
	}

	/**
	 *
	 * @param url
	 * @param param
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String postWithOutRetry(String url, Object param, int connectTime, int socketTime, Map<String, String> header) throws Exception {
		return post(url, param, connectTime, socketTime, header, true, true);
	}

	/**
	 * post调用
	 * @param url  请求地址
	 * @param param 参数
	 */
	private static String post(String url, Object param, int connectTime, int socketTime, Map<String, String> header, boolean printLog, boolean disableRetry) throws Exception {
		String paramJson = JSON.toJSONString(param);
		long startTime = System.currentTimeMillis();
		//
		Request request = Request.Post(url).bodyString(paramJson, ContentType.APPLICATION_JSON).connectTimeout(connectTime).socketTimeout(socketTime);
		//header  如果有,添加
		addHeader(request, header);
		HttpResponse resp = null;
		if (disableRetry) {
			Executor executor = disableRetryExecutor();
			resp = executor.execute(request).returnResponse();
		} else {
			resp = request.execute().returnResponse();
		}
		long endTime = System.currentTimeMillis();
		int statusCode = resp.getStatusLine().getStatusCode();
		if (printLog) {
			logger.info("url={},param={},  statusCode={}, requestTime={} ms", url, paramJson, statusCode, endTime - startTime);
		}
		if (HttpStatus.SC_OK != statusCode) {
			logger.error("url={}, statusCode={}", url, statusCode);
			throw new RuntimeException("请求失败");
		}
		String jsonData = EntityUtils.toString(resp.getEntity(), "UTF-8");
		logger.info("url={}, resultJson={}, statusCode={}", url, jsonData, statusCode);
		return jsonData;
	}

	/**
	 *
	 * @param url
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> header) throws Exception {
		return get(url, defaultConnectTime, defaultSocketTime, header);
	}

	/**
	 * get请求
	 * @param url 请求地址
	 */
	public static String get(String url, int connectTime, int socketTime, Map<String, String> header) throws Exception {
		return get(url, connectTime, socketTime, header, true);
	}

	/**
	 *
	 * @param url
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @param printLog
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, int connectTime, int socketTime, Map<String, String> header, boolean printLog) throws Exception {
		return get(url, connectTime, socketTime, header, printLog, false);
	}

	/**
	 *
	 * @param url
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String getWithOutRetry(String url, Map<String, String> header) throws Exception {
		return getWithOutRetry(url, defaultConnectTime, defaultSocketTime, header);
	}

	/**
	 *
	 * @param url
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String getWithOutRetry(String url, int connectTime, int socketTime, Map<String, String> header) throws Exception {
		return get(url, connectTime, socketTime, header, true, true);
	}

	/**
	 * get请求
	 * @param url 请求地址
	 */
	private static String get(String url, int connectTime, int socketTime, Map<String, String> header, boolean printLog, boolean disableRetry) throws Exception {
		long startTime = System.currentTimeMillis();
		Request request = Request.Get(url).connectTimeout(connectTime).socketTimeout(socketTime);
		//header  如果有,添加
		addHeader(request, header);
		HttpResponse resp = null;
		if (disableRetry) {
			Executor executor = disableRetryExecutor();
			resp = executor.execute(request).returnResponse();
		} else {
			resp = request.execute().returnResponse();
		}
		long endTime = System.currentTimeMillis();
		int statusCode = resp.getStatusLine().getStatusCode();
		if (printLog) {
			logger.info("url={},  statusCode={}, requestTime={} ms", url, statusCode, endTime - startTime);
		}
		if (HttpStatus.SC_OK != statusCode) {
			throw new RuntimeException("请求失败");
		}
		String jsonData = EntityUtils.toString(resp.getEntity(), "UTF-8");
		logger.info("url={}, resultJson={}, statusCode={}", url, jsonData, statusCode);
		return jsonData;

	}

	/**
	 *
	 * @param url
	 * @param header
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postForm(String url, Map<String, String> header, Map<String, String> params) throws Exception {
		return postForm(url, defaultConnectTime, defaultSocketTime, header, params);
	}

	/**
	 *
	 * @param url
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postForm(String url, int connectTime, int socketTime, Map<String, String> header, Map<String, String> params) throws Exception {
		return postForm(url, connectTime, socketTime, header, params, true);
	}

	/**
	 *
	 * @param url
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @param params
	 * @param printLog
	 * @return
	 * @throws Exception
	 */
	public static String postForm(String url, int connectTime, int socketTime, Map<String, String> header, Map<String, String> params, boolean printLog) throws Exception {
		return postForm(url, connectTime, socketTime, header, params, printLog, false);
	}

	/**
	 *
	 * @param url
	 * @param header
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postFormWithOutRetry(String url, Map<String, String> header, Map<String, String> params) throws Exception {
		return postFormWithOutRetry(url, defaultConnectTime, defaultSocketTime, header, params);
	}

	/**
	 *
	 * @param url
	 * @param connectTime
	 * @param socketTime
	 * @param header
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postFormWithOutRetry(String url, int connectTime, int socketTime, Map<String, String> header, Map<String, String> params) throws Exception {
		return postForm(url, connectTime, socketTime, header, params, true, true);
	}

	/**
	 * form方式post
	 * @param url 请求地址
	 * @param connectTime
	 * @param socketTime
	 * @param header 需要添加的请求头，如没有为null
	 * @param params 参数
	 * @param printLog 是否需要打印日志
	 * @return
	 * @throws Exception
	 */
	private static String postForm(String url, int connectTime, int socketTime, Map<String, String> header, Map<String, String> params, boolean printLog, boolean disableRetry) throws Exception {
		long startTime = System.currentTimeMillis();
		Request request = Request.Post(url).connectTimeout(connectTime).socketTimeout(socketTime).bodyForm(parseParam(params), Consts.UTF_8);
		//header  如果有,添加
		addHeader(request, header);
		HttpResponse resp = null;
		if (disableRetry) {
			Executor executor = disableRetryExecutor();
			resp = executor.execute(request).returnResponse();
		} else {
			resp = request.execute().returnResponse();
		}
		long endTime = System.currentTimeMillis();
		int statusCode = resp.getStatusLine().getStatusCode();
		if (printLog) {
			logger.info("url={},param={},  statusCode={}, requestTime={} ms", url, JSON.toJSONString(params), statusCode, endTime - startTime);
		}
		if (HttpStatus.SC_OK != statusCode) {
			throw new RuntimeException("请求失败");
		}
		String jsonData = EntityUtils.toString(resp.getEntity(), "UTF-8");
		logger.info("url={}, resultJson={}, statusCode={}", url, jsonData, statusCode);
		return jsonData;

	}

	private static List<NameValuePair> parseParam(Map<String, String> params) {
		List<NameValuePair> nameValuePairList = Lists.newArrayList();

		if (MapUtils.isEmpty(params)) {
			return nameValuePairList;
		}

		NameValuePair nameValuePair;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			nameValuePairList.add(nameValuePair);
		}
		return nameValuePairList;
	}

	/**
	 *
	 * @param request
	 * @param hm 需要加入header的信息
	 * @return
	 */
	private static void addHeader(Request request, Map<String, String> hm) {
		if (hm == null || hm.size() <= 0) {
			return;
		}

		for (Map.Entry<String, String> entry : hm.entrySet()) {
			request.setHeader(entry.getKey(), entry.getValue());
		}
	}

	private static Executor disableRetryExecutor() {
		HttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
		Executor executor = Executor.newInstance(httpClient);
		return executor;
	}

	/**
	 * 从网络Url中下载文件
	 * @param urlStr
	 * @param connectTimeout
	 * @throws IOException
	 */
	public static byte[] downLoadFromUrl(String urlStr, int connectTimeout) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		//设置超时间为3秒
		conn.setConnectTimeout(connectTimeout);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//得到输入流
		InputStream inputStream = null;
		ByteArrayOutputStream bos = null;
		try {
			inputStream = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			bos = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}

}