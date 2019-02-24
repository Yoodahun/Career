const ls = localStorage;

function getParameters() {
  const params = {};
  const qs = location.search;
  if (qs && qs !== '') {
    const hashes = qs.slice(1).split('&');
    for (let n in hashes) {
      const h = hashes[n].split('=');
      params[h[0]] = h[1];
    }
  }
  return params;
}

$(function() {
$('form').submit(function() {
   if($('#id').val().length < 6 || $('#password').val().length < 8) {
     alert('ログインに失敗しました。IDやパスワードを確認して下さい。');
     return false;
   }
    const save = $('#save').prop('checked');
    if (save) {
      ls.setItem('id', $('#id').val());
      ls.setItem('password', $('#password').val());
    } else {
      ls.removeItem('id');
      ls.removeItem('password');
    }
    ls.setItem('save', save);
  });

  // 初期化
  const params = getParameters();
  console.log(params);

  if (params.sc === '401') {
    alert('ログインできませんでした。正しい社員番号やパスワードを入力してください。');
  } else if (params.sc === '701') {
    // alert('ログアウトされました。');
  }

  $('#id').val(ls.getItem('id'));
  $('#password').val(ls.getItem('password'));
  $('#save').prop('checked', (ls.getItem('save') === 'true'));

$.getJSON('report/login?loginuser=out').done(function(r){
  console.log('logout');
});

});
