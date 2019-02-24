require 'test_helper'

class HomeControllerTest < ActionController::TestCase
  test "should get index" do
    get :index
    assert_response :success
  end

  test "should get login" do
    get :login
    assert_response :success
  end

  test "should get map" do
    get :map
    assert_response :success
  end

  test "should get application_form" do
    get :application_form
    assert_response :success
  end

end
