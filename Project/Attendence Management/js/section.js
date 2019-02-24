function list(values) {
  for (let key in values) {
    const d = values[key];
    const name = [d.name];
    const id = [d.id];
    $('#departId').append($('<option>').text(name).val(id));
  }
}

function editRow($table, values, method) {
  for (let key in values) {
    console.log(values);

    const d = values[key];
    const arr = [d.id, d.departName, escapeHtml(d.name)]; // id、部、課
    console.log(arr);
    let tr = null;
    if (method === 'post') {
      tr = $table.row.add(arr).draw().node();
    } else if (method === 'put') {
      tr = $table.row('.bg-primary').data(arr).node();
    }
    $('#dialog').modal('hide');
    $(tr).attr('data-departId', d.departId); // 부 코드 지정을 departId 지정해놓음 자바에서
  }
}

$(function() {
  $('form').submit(function() {
    return false;
  });
  $('button.insert').click(function() {
    $('#dialogLabel').text('追加');
    $('#method').val('post');
    $('#sectionId').prop('readonly', false);
    $('#del').prop('hidden', true);
    $('form')[0].reset();
    $('table > tbody > tr').removeClass('bg-primary'); // 실행하고 남은 파란줄 없애줌
    $('#dialog').modal('show');
  });

  $('table > tbody').on('click', 'tr', function() {
    $('#dialogLabel').text('更新');
    $('#method').val('put');
    $('#sectionId').prop('readonly', true);
    $('#del').prop('hidden', false);
    $table.$('tr.bg-primary').removeClass('bg-primary');
    const $tr = $(this);
    $tr.addClass('bg-primary');
    const d = $table.row('.bg-primary').data();
    $('#sectionId').val(d[0]);
    $('#departId').val($tr.attr('data-departId'));
    $('#sectionName').val(ReverseEscapeHtml(d[2]));
    $('#dialog').modal('show');
  });

  $('button.delete').click(function() {
    const b = confirm('削除してよろしいですか？');
    if (!b) {
      return false;
    }
    $('#method').val('delete');
    $.post(
      'report/section', {
        'sectionId': $('#sectionId').val(),
        'method': 'delete'
      }
    ).done(function(r) {
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
    $.post(
      'report/section', params
    ).done(function(r) {
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      } else {
        editRow($table, r, $('#method').val());
        $('#dialog').modal('hide');
      }
    });
  });

  $('#esc').click(function() {
    $('table > tbody > tr').removeClass('bg-primary');
  }); // 변경 시 클릭하고 캔슬했을 때 남아있는 파란줄 없애기

  /* Ajaxのとき
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
  const $table = $('table').DataTable(); // 데이터테이블 정렬 기능 찾아서 여기다가 써주기

  $.when(
    $.getJSON('report/login?loginuser=me'),
    $.getJSON('report/section'), // 카
    $.getJSON('report/depart') // 부
  ).done(function(r1, r2, r3) {
    console.log(r1);
    r1 = r1[0];
    if (!r1 || r1.statusCode === 500) {

      alert(r1.reason);
      location.href = 'index.html?sc=701';
      return false;
    }
    $('#loginuser').text(r1.data.name);
    console.log(r2);
    r2 = r2[0];
    if (!r2 || r2.statusCode === 500) {
      alert(r2.reason);
      return false;
    }
    console.log(r2.data.name);
    editRow($table, r2.data, 'post');
    console.log(r3);
    r3 = r3[0];
    if (!r3 || r3.statusCode === 500) {
      alert(r3.reason);
      return false;
    }
    list(r3.data);
  });
});
