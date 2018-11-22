/**
 * 
 */
var httpRequest = null;

function getXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}

function sendProcess(method, url, params, callback) {
	httpRequest = getXMLHttpRequest();
	httpRequest.onreadystatechange = callback;

	var httpMethod = method.toUpperCase();
	if (httpMethod != 'GET' && httpMethod != 'POST')
		httpMethod = 'GET';

	var httpParams = '';
	if (params != null) {
		for(var key in params){
			if(httpParams != '')
				httpParams += '&';
			httpParams += key + '=' + encodeURIComponent(params[key]);
		}
	}

	var httpUrl = url;
	if (httpMethod == 'GET' && params != null)
		httpUrl = url + '?' + httpParams;


	httpRequest.open(httpMethod, httpUrl, true);
	if(httpMethod == 'POST')
		httpRequest.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
	
	httpRequest.send(httpMethod == 'GET' ? null : httpParams);
}