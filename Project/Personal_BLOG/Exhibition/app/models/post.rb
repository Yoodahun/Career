class Post < ApplicationRecord
  acts_as_taggable
  belongs_to :user
  belongs_to :bulletin, optional: true
  mount_uploader :image, ImageUploader
end
