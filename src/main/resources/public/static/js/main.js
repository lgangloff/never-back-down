$(window).bind('scroll', function () {
    if ($(window).scrollTop() >= $('#main').offset().top - $('#nav').height()) {
        $('#nav').addClass('fixed');
	} 
	else {
        $('#nav').removeClass('fixed');
    }
});

$("a").click(function(event) {

	var href = $(this).attr('href');

	if (href && href.charAt(0) == '#'){
		var $section = $(href);
		if ($section.length < 1)
			return;

		event.preventDefault();

		$('html, body').animate({
			scrollTop: $section.offset().top
		}, 2000);
	}
	return;
  
});

$("#nav a").each(function(){

	var href = $(this).attr('href');
	
	if (href && href.charAt(0) == '#'){
		var $section = $(href);
		if ($section.length < 1)
			return;

		var that = this;
		$(window).bind('scroll', function () {
			if ($(window).scrollTop() >= $($section).offset().top - 1 ) {
				$("#nav a").removeClass('active');
				$(that).addClass('active');
			}
		});
		
	}
	return;

});