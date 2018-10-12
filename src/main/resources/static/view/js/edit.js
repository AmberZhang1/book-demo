layui.use(['table', 'layer', 'form', 'laydate'], function () {
  var layer = layui.layer;
  var laydate = layui.laydate;
  laydate.render({
    elem: '.date' //指定元素
  });

  var active = {
    submit: function () {
      var book = {
        id: null,
        name: null,
        author: null,
        press: null,
        type: null,
        total: null,
        price:null
      };
      book.id = $("#id").val();
      book.name = $("#name").val();
      book.author = $("#author").val();
      book.press = $("#press").val();
      book.type = $("#type").val();
      book.total = $("#total").val();
      book.price = $("#price").val();
      $.ajax({
        url: '/book/save',
        type: 'POST',
        dataType: "json",
        data: JSON.stringify(book),
        contentType: 'application/json;charset=utf-8',
        success: function (result) {
          if (result.code == 'SUCCESS') {
            layer.alert('保存成功', {icon: 1}, function () {
              parent.layer.close(window.parent.layerIndex);
            });
          } else {
            layer.msg(result.message, {icon: 2});
          }
        }
      })
    }
  };

  $("#save").on("click", active.submit);
});

/**
 * 序列化表单提交参数为对象
 * @author lily
 */
jQuery.fn.extend({
  serializeObject: function () {
    var a = this.serializeArray();
    var s = {},
        add = function (key, value) {
          value = jQuery.isFunction(value) ? value() : value;
          s[encodeURIComponent(key)] = value;
        };
    if (jQuery.isArray(a) || (a.jquery && !jQuery.isPlainObject(a))) {
      jQuery.each(a, function () {
        add(this.name, this.value);
      });

    }
    return s;
  }
});