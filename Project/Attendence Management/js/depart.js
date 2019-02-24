function editRow($table, values, method) {
  for (let key in values) {
    const d = values[key];
    const arr = [d.id, escapeHtml(d.name)];
    let tr = null;
    if (method === 'post') {
      tr = $table.row.add(arr).draw().node();
    } else if (method === 'put') {
      console.log($table.row('.bg-primary').data());
      tr = $table.row('.bg-primary').data(arr).node();
    }
  }
}

$(function() {
  $('form').submit(function() {
    return false;
  });
  $('button.insert').click(function() {
    $('#dialogLabel').text('追加');
    $('#method').val('post');
    $('#departId').prop('readonly', false);
    $('#del').prop('hidden', true);
    $('form')[0].reset();
    $('table > tbody > tr').removeClass('bg-primary'); // 실행하고 남은 파란줄 없애줌
    $('#dialog').modal('show');
  });



  $('table > tbody').on('click', 'tr', function() {
    $('#dialogLabel').text('更新');
    $('#method').val('put');
    $('#departId').prop('readonly', true);
    $('#del').prop('hidden', false);
    $table.$('tr.bg-primary').removeClass('bg-primary');
    //$('table > tbody > tr').removeClass('bg-primary');
    const $tr = $(this);
    $tr.addClass('bg-primary');
    const d = $table.row('.bg-primary').data();
    $('#departId').val(d[0]);
    $('#departName').val(ReverseEscapeHtml(d[1]));
    $('#dialog').modal('show');
  });

  $('button.delete').click(function() {
    const b = confirm('削除してよろしいですか？');
    if (!b) {
      return false;
    }
    $('#method').val('delete');
    console.log($('#departId').val());
    $.post(
      'report/depart', {
        'departId': $('#departId').val(),
        'method': 'delete'
      }
    ).done(function(r) {
      console.log(r);
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      } else {
        $table.row('tr.bg-primary').remove().draw(false);
        $('#dialog').modal('hide');
      }
    });
  });

  $('button.ok').click(function() {
    const params = $('form').serializeArray();
    console.log(params);
    $.post(
      'report/depart', params
    ).done(function(r) {
      console.log(r);
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      } else {
        const d = {
          id: r.data.id,
          name: $('#departName').val(),
        };
        console.log(d);
        editRow($table, [d], $('#method').val());
        $('#dialog').modal('hide');
      }
    });
  });

  $('#esc').click(function() {
    $('table > tbody > tr').removeClass('bg-primary');
  }); // 변경 시 클릭하고 캔슬했을 때 남아있는 파란줄 없애기

  　
  /* Ajaxのzとき
    $('a.logout').click(function() {
      $.getJSON(
        'json/login.json?loginuser=logout'
      ).done(function(r) {
        if (r.statusCode !== 200) {
          alert(r.reason);
        }
        location.href = 'index2.html';
      });
    });
    */

    $('#return').click(function() {
      location.href = 'initial.html';
    });

  // 初期化
  $.extend($.fn.dataTable.defaults, {
    language: {
      url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
    }
  });
  const $table = $('table').DataTable();

  $.when(
    $.getJSON('report/login?loginuser=me'),
    $.getJSON('report/depart')
  ).done(function(r1, r2) {
    r1 = r1[0];
    if (!r1 || r1.statusCode === 500) {

      alert(r1.reason);
      location.href = 'index.html?sc=701';
      return false;
    }
    $('#loginuser').text(r1.data.name);
    r2 = r2[0];
    if (!r2 || r2.statusCode === 500) {
      alert(r2.reason);
      return false;
    }
    editRow($table, r2.data, 'post');
  });
});
