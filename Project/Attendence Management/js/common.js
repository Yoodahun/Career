function escapeHtml(string) {
  if(typeof string !== 'string') {
    return string;
  }
  return string.replace(/[&'`"<>]/g, function(match) {
    return {
      '&': '&amp;',
      "'": '&#x27;',
      '`': '&#x60;',
      '"': '&quot;',
      '<': '&lt;',
      '>': '&gt;',
    } [match];
  });
}
function ReverseEscapeHtml(string) {
 if (typeof string !== 'string') {
   return string;
 }
 return string.replace(/(&amp;|&#x27;|&#x60;|&quot;|&lt;|&gt;)/g, function(match) {
   return {
     '&amp;': '&',
     '&#x27;': "'",
     '&#x60;': '`',
     '&quot;': '"',
     '&lt;': '<',
     '&gt;': '>',
   }[match];
 });
}
