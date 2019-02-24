class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  # protect_from_forgery with: :exception
  
  #current_user가 model에서 쓰였는데, 막상 보니까 처음에 선언 된 곳이 없었고
  #초기화 해 준곳이 없었는데
  #젬에서 해줘서 딱히 안해도 됬던건지 모르겠는데
  #구글링 좀 하니까 current_user 이런식으로 쓰라고 해서
  #controller에 넣어놉니다.
  
  # def current_user
  #   return unless session[:user_id]
  #   @current_user ||= User.find(session[:user_id])
  # end
  
  
end
