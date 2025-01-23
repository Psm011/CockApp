<%

String message1 = (String) session.getAttribute("message1");
if (message1 != null) {

%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
  <strong><%= message1 %></strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

<% 
    session.removeAttribute("message1");
}
%>

<%

String message2 = (String) session.getAttribute("message2");
if (message2 != null) {

%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
  <strong><%= message2 %></strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

<% 
    session.removeAttribute("message2");
}
%>
