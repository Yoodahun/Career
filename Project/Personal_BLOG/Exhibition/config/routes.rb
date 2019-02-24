Rails.application.routes.draw do
  get 'home/index'

  resources :message, only:[:new, :create]
   get '/message/clear' => 'message#clear'
  resources :bulletins
  devise_for :users
  resources :posts
  root 'home#index'
  #  get '/' => 'posts#index'
  post 'tags/:tag' => 'posts#index', as: :tag
  get 'tags/:tag' => 'bulletins#index'

  # mailer

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
