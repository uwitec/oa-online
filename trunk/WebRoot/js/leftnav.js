// JavaScript Document
//主页面左侧窗口列表展示
function showmenu(id) {
	var list = document.getElementById("list_" + id);
	var menu = document.getElementById("menu_" + id);
	if (list && menu) {
		if (list.style.display == "none") {
			list.style.display = "block";
			menu.className = "title1";
			addCokieMenu(id);
		} else {
			list.style.display = "none";
			menu.className = "left_nav_title";
			removeCokieMenu(id);
		}
	}
}

// 添加打开菜单
function addCokieMenu(id) {
	var cookieStr = getCookie("leftMenu");
	if (cookieStr == null) {
		setCookie("leftMenu", id, 1);
	} else {
		var indeInt = cookieStr.indexOf(id);
		if (indeInt < 0) {
			setCookie("leftMenu", cookieStr + id, 1);
		}
	}
}
// 移除打开菜单
function removeCokieMenu(id) {
	var cookieStr = getCookie("leftMenu");
	var indeInt = cookieStr.indexOf(id);
	if (indeInt >= 0) {
		setCookie("leftMenu", cookieStr.substring(0, indeInt)
				+ cookieStr.substring(indeInt + 1, cookieStr.length), 1);
	}
}

// 初始化菜单
function initMenu() {
	var cookieStr = getCookie("leftMenu");
	if (cookieStr != undefined) {
		for ( var i = 0; i < cookieStr.length; i++) {
			showmenu(cookieStr.charAt(i));
		}
	}
}

// 设置Cookie
function setCookie(sName, sValue, expireHours) {
	var cookieString = sName + "=" + escape(sValue);
	// ;判断是否设置过期时间
	if (expireHours > 0) {
		var date = new Date();
		date.setTime(date.getTime + expireHours * 3600 * 1000);
		cookieString = cookieString + "; expire=" + date.toGMTString();
	}
	document.cookie = cookieString;
}

// --- 获取cookie
function getCookie(sName) {
	var aCookie = document.cookie.split("; ");
	for ( var j = 0; j < aCookie.length; j++) {
		var aCrumb = aCookie[j].split("=");
		if (escape(sName) == aCrumb[0])
			return unescape(aCrumb[1]);
	}
	return null;
}