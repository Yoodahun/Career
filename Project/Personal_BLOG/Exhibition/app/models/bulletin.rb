class Bulletin < ApplicationRecord
  acts_as_taggable
  has_many :posts, dependent: :destroy
end
