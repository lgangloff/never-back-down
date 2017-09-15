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
		}, 500);
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

/*
(function ($) {

	$(function () {

		var $window = $(window),
			$body = $('body'),
			$main = $('#main');

/*
		// Shrink effect.
		$main.scrollex({
			mode: 'top',
			enter: function () {
				$nav.addClass('fixed');
			},
			leave: function () {
				$nav.removeClass('fixed');
			},
		});

		// Links.
		var $nav_a = $nav.find('a');

		$nav_a.scrolly({
			speed: 1000,
			offset: function () { return $nav.height(); }
		})
		.on('click', function () {

			var $this = $(this);

			// External link? Bail.
			if ($this.attr('href').charAt(0) != '#')
				return;

			// Deactivate all links.
			$nav_a
				.removeClass('active')
				.removeClass('active-locked');

			// Activate link *and* lock it (so Scrollex doesn't try to activate other links as we're scrolling to this one's section).
			$this
				.addClass('active')
				.addClass('active-locked');

		})
		.each(function () {

			var $this = $(this),
				id = $this.attr('href'),
				$section = $(id);

			// No section for this link? Bail.
			if ($section.length < 1)
				return;

			// Scrollex.
			$section.scrollex({
				mode: 'middle',
				enter: function () {

					// Activate section.
					$section.removeClass('inactive');

					// No locked links? Deactivate all links and activate this section's one.
					if ($nav_a.filter('.active-locked').length == 0) {

						$nav_a.removeClass('active');
						$this.addClass('active');

					}

					// Otherwise, if this section's link is the one that's locked, unlock it.
					else if ($this.hasClass('active-locked'))
						$this.removeClass('active-locked');

				}
			});

		});
);

})(jQuery);

*/