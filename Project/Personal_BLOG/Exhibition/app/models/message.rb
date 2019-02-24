class Message
  include ActiveModel::Model
  attr_accessor :email, :title, :message
  validates :title, :email, :message, presence: true
end
