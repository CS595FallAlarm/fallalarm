

(function($,W,D,undefined)
{
    //manage portfolio button clicks to populate page content

    W.JQMP = W.JQMP || {};
    W.JQMP = {

        init: function()
        {

            //contact page
            $('a[href=#contact]').bind('click', function(e)
            {
                $.getScript('js/jquery.validate.min.js', function()
                {
                    $.getScript('js/contact.js', function()
                    {
                        $.mobile.hidePageLoadingMsg();
                    });
                });
            });

    	    // Pop up Dialog Box
            $(document).delegate('#simplestring', 'click', function () {
                $(this).simpledialog({
                    'mode': 'string',
                    'prompt': 'You are about to send message',
                    'buttons': {
                        'OK': {
                            click: function () {
                                $('#dialogoutput').text($('#dialoglink').attr('data-string'));
                            }
                        },
                        'Cancel': {
                            click: function () { },
                            icon: "delete",
                            theme: "c"
                        }
                    }
                })
            });

            //portfolio gallery pages
            $('a[href=#portfolio3]').bind('click', function(e)
            {
                var myPhotoSwipe1 = $("#gallery1 a").photoSwipe({ enableMouseWheel: false , enableKeyboard: false });
            });

            //portfolio gallery pages
            $('a[href=#portfolio4]').bind('click', function(e)
            {
                var myPhotoSwipe2 = $("#gallery2 a").photoSwipe({ enableMouseWheel: false , enableKeyboard: false });
            });

            //social links - version 1 all links open direct in new window
            $('#social-links a').bind('click', function(e)
            {
                e.preventDefault();
                var sn = $(this).attr('href').replace('#','');
                console.log(sn);
                W.location = W.JQMP.SETTINGS.SOCIAL[sn];
            });

        }
    }

    $(D).ready(function()
    {
        JQMP.init();
    });

})(jQuery,window,document);