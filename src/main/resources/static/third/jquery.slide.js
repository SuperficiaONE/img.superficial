/**
 * 幻灯片切换
 * @Description: 封装几种幻灯片切换插件
 *
 * @Author: JZT.Oscar
 * @Date: 2018-03-27 09:00:00
 * @Last Modified by: JZT.Oscar
 * @Last Modified time: 2018-04-16 09:18:31
 */

(function ($) {

	var Slider = function (target) {
		this.wrap = $(target);
		this.defaults = {
			body: ".slideList",
			prev: ".prev",
			next: ".next",
			width: null,

			speed: 1000,
			auto: true,
			time: 3,
			minCount: 5, // 最小项目数量
			stepCount: "auto", // 每次轮播步长:auto：自动，超过步长阈值后，以最小数目进行轮播，反之，以单个项目轮播；可直接设定步长值
			stepThreshold: 12, // 步长阈值：超过指定数目后，切换方式发生变化，
			completed: null, // 动画完成时
			loop: true //是否循环补全
		};

		this.isPause = false;
		this.iNum = 0;
		this.iTimer = null;
	};
	Slider.prototype = {
		// 初始化
		init: function (options) {
			var that = this;

			if (options) {
				$.extend(this.defaults, options);
			}

			this.oUl = $(this.defaults.body, this.wrap);
			this.oItems = this.oUl.find("li");
			this.iWidth = parseInt(this.defaults.width) || this.oItems.eq(0).outerWidth(true);
			this.iLen = this.oItems.length;

			if (this.iLen < this.defaults.minCount) {
				$(this.defaults.prev, this.wrap).hide();
				$(this.defaults.next, this.wrap).hide();
				return;
			}

			// 非循环模式下，初始化左箭头不显示
			if (!this.defaults.loop) {
				this.iNum == 0 ? $(this.defaults.prev, this.wrap).hide():$(this.defaults.prev, this.wrap).show();
			}

			// 显示方向箭头
			// $(this.defaults.prev, this.wrap).show();

			$(this.defaults.next, this.wrap).show();

			// 轮播个数
			if (this.defaults.stepCount == "auto") {
				this.defaults.stepCount = this.iLen >= this.defaults.stepThreshold ? this.defaults.minCount : 1;
			}

			if (this.defaults.prev != null) {
				if (typeof this.defaults.prev === "string") {
					this.defaults.prev = $(this.defaults.prev, this.wrap);
				}
				this.defaults.prev.click(function(){
					that.prev();
				});
			}
			if (this.defaults.next != null) {
				if (typeof this.defaults.next === "string") {
					this.defaults.next = $(this.defaults.next, this.wrap);
				}
				this.defaults.next.click(function(){
					that.next();
				});
			}

			if (this.defaults.loop) {

			// 复制
			this.oUl
				.append(this.oItems.clone().addClass("u_slide_clone"))
				.append(this.oItems.clone().addClass("u_slide_clone"))
				.hover(
					function () {
						that.isPause = true;
					},
					function () {
						that.isPause = false;
					}
				);

			} else {
				this.oUl.hover(
					function () {
						that.isPause = true;
					},
					function () {
						that.isPause = false;
					}
				);
			}


			this.start();
		},
		// 后退一步是人生
		prev: function () {
			
			if (!this.oUl.is(":animated")) {
				
					
					if (this.iNum <= this.iLen) {
						if (this.defaults.loop) {
						this.iNum = this.iLen * 2 - (this.iLen - this.iNum);
						this.oUl.css({
							left: -this.iNum * this.iWidth + "px"
						});
						}
					}
				
				

				this.iNum -= this.defaults.stepCount;

				console.log(this.iNum);
				console.log(this.iLen);
				this.iNum == 0 ? $(this.defaults.prev, this.wrap).hide():$(this.defaults.prev, this.wrap).show();
				this.iNum < this.iLen ? $(this.defaults.next, this.wrap).show():$(this.defaults.next, this.wrap).hide();

				this.move();
			}
		},
		// 往前一步是黄昏
		next: function () {
			$(this.defaults.prev, this.wrap).show();

			if (!this.oUl.is(":animated")) {
				if (this.iNum >= this.iLen) {
					this.iNum = this.iNum - this.iLen;
					this.oUl.css({
						left: -this.iNum * this.iWidth
					});
				}

				this.iNum += this.defaults.stepCount;

				if (!this.defaults.loop) {
					if ((this.iLen-this.iNum)<=this.defaults.stepCount) {
						$(this.defaults.next, this.wrap).hide();
					}
				}

				console.log(this.iNum);
				console.log(this.iLen);
				
				this.move();
			}
		},
		// 移动
		move: function () {
			var that = this;

			if (!this.oUl.is(":animated")) {
				this.stop();

				this.oUl.animate({
					left: -this.iNum * this.iWidth + "px"
				}, this.defaults.speed, function () {
					if (that.defaults.completed) that.defaults.completed(that.iNum);

					that.start();
				});
			}
		},
		// 联通
		start: function () {
			var that = this;
			if (this.defaults.auto) {
				this.iTimer = setInterval(function () {
					if (!that.isPause) {
						if (that.iNum >= that.iLen) that.iNum = 0;

						that.iNum += that.defaults.stepCount;

						that.move();
					}
				}, this.defaults.time * 1000);
			}
		},
		// 电信
		stop: function () {
			if (!!this.iTimer) clearInterval(this.iTimer);
		}
	};

	$.fn.imgSlide = function (options) {
		/// <summary>图片横向滚动(多图显示，单张滚动)</summary>
		/// <param name="options" type="Object">对象参数,可选参数：
		/// <br/>prev：选择器或对象,默认.prev
		/// <br/>next：选择器或对象,默认.next
		/// <br/>
		/// <br/>speed：Int,图片滑动速度,越小越快,默认1000
		/// <br/>auto：Boolean,是否自动切换,默认true
		/// <br/>time：Int,自动切换时间(秒),默认3秒
		/// </param>
		return this.each(function () {
			return new Slider(this).init(options);
		});
	};
})(jQuery);