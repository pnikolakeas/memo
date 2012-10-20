function isNil(obj) {
	return obj == null || typeof obj == 'undefined';
}

function isObject(obj) {
	return !isNil(obj) && typeof obj == 'object';
}

function isScalar(obj) {
	return !isNil(obj) && typeof obj != 'object' && typeof obj != 'function';
}

function isPrimitive(obj) {
	return isString(obj) || isNumber(obj) || this.isBoolean(obj);
}

function isBoolean(obj) {
	return typeof obj == 'boolean' || obj instanceof Boolean;
}

function isNumber(obj) {
	return typeof obj == 'number' || obj instanceof Number;
}

function isString(obj) {
	return typeof obj == 'string' || obj instanceof String;
}

function isArray(obj) {
	return obj instanceof Array;
}

function obj(cfg) {
	obj.cp(this, cfg);

	if (this.init) {
		this.init();
	}
}

obj.cp = function(to) {
	if (!isObject(to)) to = {};

	for (var i = 1; i < arguments.length; i++) {
		var from = arguments[i];

		if (isObject(from)) {
			for (var p in from) {
				to[p] = from[p];
			}
		}
	}

	return to;
};

obj.ns = function(str, val) {
	str = str || '';
	val = val || {};

	var ref = window;
	var parts = str.split('.');

	for (var x = 0; x < parts.length; x++) {
		var p = parts[x];

		if (!ref[p]) {
			ref[p] = {};
		}

		ref = ref[p];
	}

	obj.cp(ref, val);
};

function showLoadingMask() {
	window.loadingMaskId = setTimeout(function() {
		$('.ajaxOverlay').fadeIn('fast');
		// $('.ajaxOverlay').show();
	}, 800);
}

function hideLoadingMask() {
	clearTimeout(window.loadingMaskId);
	$('.ajaxOverlay').fadeOut('fast');
	// $('.ajaxOverlay').hide();
}

$(function() {
	$("<div class='ajaxOverlay'>"
	+ "<div class='ajaxIndicator'></div>"
	+ "</div>").appendTo("html > body");
});
