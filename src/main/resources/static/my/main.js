var config_validator = {
    ignore: 'input[type=hidden], .select2-input', // ignore hidden fields
    errorClass: 'validation-error-label',
    successClass: 'validation-valid-label',
    highlight: function (element, errorClass) {
        $(element).removeClass(errorClass);
    },
    unhighlight: function (element, errorClass) {
        $(element).removeClass(errorClass);
    },

    // Different components require proper error label placement
    errorPlacement: function (error, element) {

        // Styled checkboxes, radios, bootstrap switch
        if (element.parents('div').hasClass("checker") || element.parents('div').hasClass("choice") || element.parent().hasClass('bootstrap-switch-container')) {
            if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
                error.appendTo(element.parent().parent().parent().parent());
            }
            else {
                error.appendTo(element.parent().parent().parent().parent().parent());
            }
        }

        // Unstyled checkboxes, radios
        else if (element.parents('div').hasClass('checkbox') || element.parents('div').hasClass('radio')) {
            error.appendTo(element.parent().parent().parent());
        }

        // Inline checkboxes, radios
        else if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
            error.appendTo(element.parent().parent());
        }

        // Input group, styled file input
        else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
            error.appendTo(element.parent().parent());
        }
        else {
            error.insertAfter(element);
        }
    },
    validClass: "validation-valid-label",
    success: function (label) {
        label.addClass("validation-valid-label").text("Success.")
    },
    rules: {},
    messages: {
        custom: {
            required: "This is a custom error message",
        },
        agree: "Please accept our policy"
    }
};
var BlockPanel;
BlockPanel = function () {
    this.message = '<i class="icon-spinner4 spinner"></i>';
    this.backgroundColorOverlay = '#ddd';
    this.backgroundColorCss = 'transparent'
    this.element = "body";
    this.opacity = 0.5;


    this.block = function () {
        $(this.element).block({
            message: this.message,
            overlayCSS: {
                backgroundColor: this.backgroundColorOverlay,
                opacity: this.opacity,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: this.backgroundColorCss
            }
        });
    };
    this.unBlock = function () {
        $(this.element + ' .blockUI').remove();
    };
    this.unBlockAll = function () {
        $('.blockUI').remove();
    }
};
var SearchProduct = function () {
        var vm = this;
        var selectedCategoryCode;
        var data = {};
        var MARKET_TEMPLATE = "#market_template";
        var MARKET_ID = "#markets";

        var CATE_TEMPLATE = "#cate_template";
        var CATE_ID = "#categories";

        var get_source = function () {
            return $('input[name=store_radio]:checked').val();
        };

        var render = function (data, src, des) {
            var html;
            var source = $(src).html();

            if (data) {
                if (data.length === 0) {
                    $(des).html('No data');
                }

                $(des).html(html);
                var template = Handlebars.compile(source);
                html = template(data);
            } else {
                html = source;
            }

            $(des).html(html);
            blockBody.unBlock();
        };
        var append = function (data, src, des) {
            var html;
            var source = $(src).html();

            if (data) {
                if (data.length === 0) {
                    $(des).html('No data');
                }

                $(des).html(html);
                var template = Handlebars.compile(source);
                html = template(data);
            } else {
                html = source;
            }

            $(des).append(html);
            blockBody.unBlock();
        };
        var build_market = function (source) {
            $.get("/rest/search/advance/get-marketplace?store=" + source,
                function (result) {
                    data.marketplaces = result;
                    render(data, MARKET_TEMPLATE, MARKET_ID);

                });

        };
        var get_selected_market_id = function () {
            return $(MARKET_ID + ' select').val();

        };

        var get_selected_market = function () {
            var selected_market_id = get_selected_market_id();
            //TODO can sua lai
            if (!selected_market_id) {
                selected_market_id = 21;
            }
            var marketplaces = data.marketplaces;
            if (marketplaces) {
                return marketplaces.filter(function (market) {
                    return market.id == selected_market_id;
                })
            }
            return [];
        };

        vm.set_current_category_code = function (el) {
            vm.selectedCategoryCode = $(el).find('.category_code').text();
        };
        var build_cat = function (source) {

            render(null, CATE_TEMPLATE, CATE_ID);
            $('#categories input').typeahead({
                input: '#categories input',
                minLength: 1,
                order: "asc",
                dynamic: true,
                delay: 500,
                backdrop: {
                    "background-color": "#fff"
                },
                display: ["code", "name"],
                template: function (query, item) {
                    var color = "#777";
                    if (item.status === "owner") {
                        color = "#ff1493";
                    }
                    searchProduct.selectedCategoryCode = null;
                    return '<span onclick="searchProduct.set_current_category_code(this)">' +
                        '<span class="category_code">{{code}}</span>' +
                        '<span class="id">({{name}})</span>' +
                        "</span>"
                },
                source: {
                    ajax: function (query) {
                        return {
                            type: "GET",
                            url: window.location.origin + rootContext + "rest/search/advance/get-categories?store=" +
                            source + "&market_id=" + get_selected_market_id() +
                            "&query=" + query,

                        }
                    }
                }
            });

        };


        this.showAdvance = function () {
            blockBody.block();
            var source = get_source();
            switch (source) {
                case "all":
                    $("#advance_detail").html('<div id="markets"/><div id="categories"/>');
                    // Do amazon search all ko ra du lieu, nen tao them truong market
                    build_market("amazon.com");
                    blockBody.unBlock();
                    break;
                case "amazon.com":
                case "ebay.com": {
                    build_market(source);
                    build_cat(source);
                    break;
                }
                default: {
                    build_cat(source);
                    break;
                }
            }
        };

        function get_selected_category_code(category_input) {
            var categories = data.categories;
            if (category_input && categories) {
                return categories.filter(function (cat) {
                    return cat.name === category_input
                })
            } else {
                return [];
            }

        };

        function prepare_all_store_page_result() {
            $('#search_all').show();
            $('#search_one').hide();
        }

        function prepare_one_store_page_result() {
            $('#search_one').show();
            $('#search_all').hide();
        }

        function build_search_url(prefix, keyword, min_price, max_price, store, market, category_code) {
            var keyword = keyword ? keyword : "";
            var min_price = min_price ? min_price : "";
            var max_price = max_price ? max_price : "";
            var market_name = market ? market.name : "";
            var category_code = category_code ? category_code : "";

            return "/rest/" + prefix + "?keyword=" + keyword + "&min_price=" + min_price + "&max_price=" + max_price +
                "&market=" + market_name + "&store=" + store + "&category_code=" + category_code;


        }

        function update_html_search_result(element, data) {
            render(data, "#tr_result", element + " .tbody_result")
        }

        function append_html_search_result(element, data) {

        }

        function search_all_and_update_html(keyword, min_price, max_price) {
            //clear data search all
            $("#search_all #allstore_tab .tbody_result").html("");
            debugger;
            $.get(build_search_url("search", keyword, min_price, max_price, "amazon.com", get_selected_market()[0], null), function (data) {
                if (!data && data.length != 0) {
                    update_html_search_result("#search_all #amazon-tab", data);
                    append_html_search_result("#search_all #allstore_tab", data);
                }
            });
            $.get(build_search_url("search/simple", keyword, min_price, max_price, "ebay.com", null, null), function (data) {
                if (!data && data.length != 0) {
                    update_html_search_result("#search_all #ebay-tab", data);
                    append_html_search_result("#search_all #allstore_tab", data);
                }
            });
            $.get(build_search_url("search/simple", keyword, min_price, max_price, "alibaba.com", null, null), function (data) {
                    if (!data && data.length != 0) {
                        update_html_search_result("#search_all #taobao-tab", data);
                        append_html_search_result("#search_all #allstore_tab", data);
                    }
                }
            );
            $.get(build_search_url("search/simple", keyword, min_price, max_price, "tabao.com", null, null), function (data) {
                    if (!data && data.length != 0) {
                        update_html_search_result("#search_all #alibaba-tab", data);
                        append_html_search_result("#search_all #allstore_tab", data);
                    }
                }
            );
            $.get(build_search_url("search/simple", keyword, min_price, max_price, "rakute.com", null, null), function (data) {
                    if (!data && data.length != 0) {
                        update_html_search_result("#search_all #rakute-tab", data);
                        append_html_search_result("#search_all #allstore_tab", data);
                    }
                }
            );
        }

        function search_and_update_html(keyword, min_price, max_price, source, market_place, category_code) {
            $("#search_one .tbody_result").html("");

            $.get(build_search_url("search", keyword, min_price, max_price, source, market_place, category_code), function (data) {
                if (!data && data.length != 0) {
                    update_html_search_result("#search-one table", data);
                    append_html_search_result("#search-one table tr", data);
                }
            });

        }

        this.search = function () {
            var keyword = $('#keyword').val();
            var min_price = $('#min_price').val();
            var max_price = $('#max_price').val();
            var source = get_source();
            var category_code = this.selectedCategoryCode;
            if (!keyword || keyword == "") {
                alert("keyword not empty !!!");
                return;
            }
            switch (source) {
                case "all": {
                    prepare_all_store_page_result();
                    search_all_and_update_html(keyword, min_price, max_price);
                    break;
                }
                case "ebay.com":
                case "amazon.com": {
                    var market_place = get_selected_market()[0];
                    if (market_place) {
                        prepare_one_store_page_result();
                        search_and_update_html(keyword, min_price, max_price, source, market_place, category_code);
                    } else {
                        //TODO
                        console.log("market not found")
                    }

                }
            }

        }

        function init() {
            build_market("amazon.com");
        }

        init();
    }
;
