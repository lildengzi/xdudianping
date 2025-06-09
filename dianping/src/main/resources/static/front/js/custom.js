/**
 * 提示框
 * @param text
 * @param icon
 * @param hideAfter
 */
function showMsg(text, icon, hideAfter) {
    $.toast({
        text: text,
        heading: "Tips",
        icon: icon,
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: hideAfter,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff'
    });
}

function showErrorMsg(text) {
    if (heading == undefined) {
        var heading = "Tips";
    }
    $.toast({
        text: text,
        heading: heading,
        icon: "error",
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: 1000,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff'
    });
}


function showMsgAndReload(text, icon, hideAfter) {
    $.toast({
        text: text,

        icon: icon,
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: hideAfter,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff',
        afterHidden: function () {
            window.location.reload();
        }
    });
}

function showMsgAndRedirect(text, icon, hideAfter, redirectUrl) {
    $.toast({
        text: text,
        heading: "Tips",
        icon: icon,
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: hideAfter,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff',
        afterHidden: function () {
            window.location.href = redirectUrl;
        }
    });
}


// 快速登录
$(document).on('click', '#quickLogin', function () {
    $.ajax({
        type: 'POST',
        url: '/login',
        async: false,
        data: {
            'userName': $('#userName').val(),
            'userPass': $('#userPass').val()
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
                // showMsg(data.msg, "error", 2000);
            }
        }
    });
});


function indexSearch() {
    let searchType = $('#searchType').val();
    let searchValue = $('#searchValue').val();
    if (searchType == 'dish') {
        window.location.href = '/dish?keywords=' + searchValue;
    } else if (searchType == 'store') {
        window.location.href = '/store?keywords=' + searchValue;
    }
}

$(document).on('click', '.commentStar', function () {
    $('#score').val($(this).attr('data-value'));
});
$(document).on('click', '.commentStar2', function () {
    $('#environmentScore').val($(this).attr('data-value'));
});
$(document).on('click', '.commentStar3', function () {
    $('#serviceScore').val($(this).attr('data-value'));
});
$(document).on('click', '.commentStar4', function () {
    $('#tasteScore').val($(this).attr('data-value'));
});


function saveDishMark(id) {
    $.ajax({
        type: 'POST',
        url: '/mark/saveDishMark',
        async: false,
        data: {
            'id': id,
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}


function saveStoreMark(id) {
    $.ajax({
        type: 'POST',
        url: '/mark/saveStoreMark',
        async: false,
        data: {
            'id': id,
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}


function saveAddCart(id) {
    $.ajax({
        type: 'POST',
        url: '/cart/save',
        async: false,
        data: {
            'id': id,
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}

function cancelDishMark(id) {
    $.ajax({
        type: 'POST',
        url: '/mark/cancelDishMark',
        async: false,
        data: {
            'id': id,
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}


function cancelStoreMark(id) {
    $.ajax({
        type: 'POST',
        url: '/mark/cancelStoreMark',
        async: false,
        data: {
            'id': id,
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}


function cancelAddCart(id) {
    $.ajax({
        type: 'POST',
        url: '/cart/cancel',
        async: false,
        data: {
            'id': id,
        },
        success: function (data) {
            if (data.code == 1) {
                window.location.reload();
            } else {
                alert(data.msg);
            }
        }
    });
}

// new CBPFWTabs(document.getElementById('tabs'));


$(document).ready(function () {
    $('#showCart').hover(
        function () {
            loadCart();
        },
        function () {
            $('#cartContent').hide();
        }
    );
});

function loadCart() {
    $.ajax({
        type: 'GET',
        url: '/cart/list',
        async: false,
        success: function (data) {
            data = data.result;
            if (data.length == 0) {
                let htmlContent = '<div class="v-popover-content left-entry-popover" th:if="${cart.size() == 0}">\n' +
                    '                                        <div class="mycart" live-visible="false" client-type="mac" style="color: #3578fa; height: 80px; text-align: center">\n' +
                    '                                            暂无数据\n' +
                    '                                        </div>\n' +
                    '                                    </div>';
                $('#cartContent').html(htmlContent);
                $('#cartContent').show();
            } else {
                let leftContent = '';
                let rightContent = '';
                $.each(data, function (index, item) {
                    if (index < 4) {
                        leftContent += '<a href="/dish/' + item.id + '" class="mycart-left-item">' +
                            '                <div class="mycart-left-item-img" style="background-image: url(&quot;' + item.photo + '&quot;);"></div>' +
                            '                <div class="mycart-left-item-title">' + item.name + '</div>' +
                            '            </a>'
                    }
                });
                $.each(data, function (index, item) {
                    if (index < 6) {
                        rightContent += ' <div class="mycart-right-list-item">' +
                            '  <a href="/dish/' + item.id + '"><span class="mycart-right-list-item-index">' + (index + 1) + '</span><span class="mycart-right-list-item-text">' + item.name + '</span></a>' +
                            ' </div>'
                    }
                });

                let htmlContent = '<div class="v-popover-content left-entry-popover">' +
                    '    <div class="mycart" live-visible="false" client-type="mac">' +
                    '        <div class="mycart-left">';
                htmlContent += leftContent;

                htmlContent += '     </div>' +
                    '        <div class="mycart-right">' +
                    '            <div class="mycart-right-title">不确定再看看</div>' +
                    '            <div class="mycart-right-list">';

                htmlContent += rightContent;
                htmlContent += '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>';
                $('#cartContent').html(htmlContent);
                $('#cartContent').show();
            }
        }
    });
}