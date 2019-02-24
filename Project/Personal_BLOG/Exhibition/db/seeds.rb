# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
  user = User.new
  user.email = 'tty4032@naver.com'
  user.password = 'ekgns123!'
  user.password_confirmation = 'ekgns123!'
  user.save!
