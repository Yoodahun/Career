function list(values, type) {
  for (let key in values) {
    const d = values[key];
    const name = [d.name];
    const id = [d.id];
    if (type === 'depart') {
      $('#depart').append($('<option>').text(name).val(id));
        $('#depart > option:nth-child(1)').attr('selected', 'selected');
    } else if (type === 'section') {
      $('#section').append($('<option>').text(name).val(id));
          $('#section > option:nth-child(1)').attr('selected', 'selected');

    } else if (type === 'position') {
      $('#position').append($('<option>').text(name).val(id));
      if (id === '1') {
        $('#depart').prop('disabled', true);
        $('#section').prop('disabled', true);
      } else {
        $('#depart').prop('disabled', false);
        $('#section').prop('disabled', false);
      }
    }
  }
}

function editRow($table, values, method) {
  for (let key in values) {
    const d = values[key];
    const arr = [d.id, escapeHtml(d.name), d.departName, d.sectionName, d.positionName];
    let tr = null;
    if (method === 'post') {
      tr = $table.row.add(arr).draw().node();
    } else if (method === 'put') {
      tr = $table.row('.bg-primary').data(arr).node();
    }
    $(tr).attr('data-departId', d.departId);
    $(tr).attr('data-sectionId', d.sectionId);
    $(tr).attr('data-positionId', d.positionId);
  }
}

$(function() {
  $('form').submit(function() {
    return false;
  });

  $('button.insert').click(function() {
    $('#dialogLabel').text('追加');
    $('#method').val('post');
    $('#pass').prop('readonly', false);
    $('#depart').prop('disabled', true);
    $('#section').prop('disabled', true);
    $('#del').prop('hidden', true);
    $('form')[0].reset();
    $('#dialog').modal('show');
  });

  $('table > tbody').on('click', 'tr', function() {
    $('#dialogLabel').text('更新');
    $('#method').val('put');
    $('#pass').prop('readonly', true);
    $('#del').prop('hidden', false);
    $table.$('tr.bg-primary').removeClass('bg-primary');
    const $tr = $(this);
    $tr.addClass('bg-primary');
    const d = $table.row('.bg-primary').data();
    $('#id').val(d[0]);
    $('#name').val(ReverseEscapeHtml(d[1]));
    $('#position').val($tr.attr('data-positionId'));
    $('#depart').val($tr.attr('data-departId'));

    /*　役員だったら所属部署はない　*/
    if ($tr.attr('data-positionId') === '1') {
      $('#depart').prop('disabled', true);
      $('#section').prop('disabled', true);
    } else if ($tr.attr('data-positionId') === '2') {
      $('#depart').prop('disabled', false);
      $('#section').prop('disabled', true);
    } else {
      $('#depart').prop('disabled', false);
      $('#section').prop('disabled', false);
      // $('#depart').val($tr.attr('data-departId'));
      $.getJSON(
        'report/section', {
          'departId': $('#depart').val()
        }
      ).done(function(r1) {
        console.log($tr.attr('data-sectionId'));
        console.log(r1.data);


        if (!r1 || r1.statusCode === 500) {
          //location.href = 'index2.html?sc=701';
          alert(r1.reason);
          return false;
        }
        $('#section > option').remove();
        list(r1.data, 'section');
        $('#section').val($tr.attr('data-sectionId'));
      });
      console.log($tr);

      $('#section').val();

      console.log($('#section').val());
      console.log($('#section').text());
    }
    $('#dialog').modal('show');
  });

  $('button.delete').click(function() {
    const b = confirm('削除してよろしいですか？');
    if (!b) {
      return false;
    }

    $.post(
      'report/emp', {
        'id': $('#id').val(),
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
    const params = $('form').serializeArray()
    console.log(params);
    if (params[3].value.length < 8 && params[3].value.length > 0 ) {
      alert('パスワードは8文字以上で入力して下さい。');
      return false;
    }
    $.post(
      'report/emp', params
    ).done(function(r) {
      if (!r || r.statusCode === 500) {
        alert(r.reason);


        return false;


      } else {

        editRow($table, [r.data], $('#method').val());
        $('#dialog').modal('hide');
      }
    });
  });

  $('#depart').on('change', function() {
    const depart = $(this).val();
    $.getJSON(
      'report/section', {
        'departId': depart
      }
    ).done(function(r1) {

      if (!r1 || r1.statusCode === 500) {
        //location.href = 'index2.html?sc=701';
        alert(r1.reason);
        return false;
      }
      $('#section > option').remove();
      list(r1.data, 'section');
    });
  });

  $('#position').on('change', function() {
    const position = $(this).val();
    if (position === '1') { //役員のとき
      $('#depart').prop('disabled', true);
      $('#section').prop('disabled', true);
    } else if (position === '2') { //部長のとき
      $('#depart').prop('disabled', false);
      $('#section').prop('disabled', true);
      $.getJSON('report/position').done(function(r){
        if (!r || r.statusCode === 500) {
          alert(r.reason);
          return false;
        }
        list(r.data, 'depart');
      });
    } else {
      $('#depart').prop('disabled', false);
      $('#section').prop('disabled', false);
      $.getJSON('report/position').done(function(r){
        if (!r || r.statusCode === 500) {
          alert(r.reason);
          return false;
        }
        list(r.data, 'depart');
      });
      $.getJSON('report/section').done(function(r){
        if (!r || r.statusCode === 500) {
          alert(r.reason);
          return false;
        }
        list(r.data, 'section');
      });
    }

  });

  $('#esc').click(function() {
    $('table > tbody > tr').removeClass('bg-primary');
  }); // 변경 시 클릭하고 캔슬했을 때 남아있는 파란줄 없애기

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

  $.getJSON('report/login?loginuser=me').done(function(r1) {
    // r1 = r1[0];
    if (!r1 || r1.statusCode === 500) {

      alert(r1.reason);
      location.href = 'index.html?sc=701';
      return false;
    }
    $('#loginuser').text(r1.data.name);

  });

  $.when(
    $.getJSON('report/emp'),
    $.getJSON('report/position'),
    $.getJSON('report/depart')
  ).done(function(r2, r3, r5) {



    r2 = r2[0];
    if (!r2 || r2.statusCode === 500) {
      alert(r2.reason);
      return false;
    }
    editRow($table, r2.data, 'post');

    r3 = r3[0];
    if (!r3 || r3.statusCode === 500) {
      alert(r3.reason);
      return false;
    }
    list(r3.data, 'position');

    r5 = r5[0];
    if (!r5 || r5.statusCode === 500) {
      alert(r5.reason);
      return false;
    }
    list(r5.data, 'depart');

  });
});
