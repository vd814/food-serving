(function($){
    var config = {};

    $.loading = function (options) {

        var opts = $.extend(
            $.loading.default,
            options
        );

        config = opts;
        init(opts);

        var selector = '#' + opts.id;

        $(document).on('ajaxStart', function(){
            if (config.ajax) {
                $(selector).show();
            }
        });

        $(document).on('ajaxComplete', function(){
            setTimeout(function(){
                $(selector).hide();
            }, opts.minTime);
        });

        return $.loading;
    };

    $.loading.open = function (time) {
        var selector = '#' + config.id;
        $(selector).show();
        if (time) {
            setTimeout(function(){
                $(selector).hide();
            }, parseInt(time));
        }
    };

    $.loading.close = function () {
        var selector = '#' + config.id;
        $(selector).hide();
    };

    $.loading.ajax = function (isListen) {
        config.ajax = isListen;
    };

    $.loading.default = {
        ajax       : true,
        id         : 'smloader',
        minTime    : 50,
    };

    function init (opts) {
       
        //var html = '<div class="loader loader-1" id="wait"><span></span><span></span><span></span></div>';
        var html = '<div class="loader-overlay" id="smloader"><div class="loader-content"><div class="lds-roller"><div class="lds-dual-ring"></div></div></div></div>';
        
        $(document).find('body').append(html);
    }

})(window.jQuery||window.Zepto);