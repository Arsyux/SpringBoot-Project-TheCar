let indexObject = {
	
	init: function() {
		let _this = this;

		$("#btn-goTop").on("click", () => {
			_this.goTop();
		});
		
	},

	goTop: function(){
		$('body, html').animate({ scrollTop: 0 }, 0); 
	},
}

indexObject.init();