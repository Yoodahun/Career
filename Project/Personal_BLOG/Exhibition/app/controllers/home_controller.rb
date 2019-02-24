class HomeController < ApplicationController
  def index
    @photo_background = Post.all.sample
    render layout: '_homeLayout'
  end
end
