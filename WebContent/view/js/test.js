$(function($) {
	console.log("test");
	$.extend({
		min : function() {
			console.log("min")
		},
		max : function() {
			console.log("max");
		}
	});
	
	$.max();
});
