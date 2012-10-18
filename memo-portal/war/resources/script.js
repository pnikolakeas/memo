function showLoadingMask() {
	window.loadingMaskId = setTimeout(function() {
		delete window.loadingMaskId;
		$('.ajaxOverlay').fadeIn('fast');
		// $('.ajaxOverlay').fadeIn(100);
		// $('.ajaxOverlay').show();
	}, 800);
}

function hideLoadingMask() {
	// console.log(window.loadingMaskId);
	clearTimeout(window.loadingMaskId);
	$('.ajaxOverlay').fadeOut('fast');
	// $('.ajaxOverlay').fadeOut(100);
	// $('.ajaxOverlay').hide();
}

$(function() {
	$("<div class='ajaxOverlay'>"
	+ "<div class='ajaxIndicator'></div>"
	+ "</div>").appendTo("html > body");
});
