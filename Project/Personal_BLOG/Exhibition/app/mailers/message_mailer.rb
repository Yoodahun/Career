class MessageMailer < ApplicationMailer
  require 'mailgun'
  default to: 'tty4032@naver.com'
  def contact(m)
    mail from: m.email, to: "tty4032@naver.com", subject: m.title, body: m.message

    #@user = m
    #mg_client = Mailgun::Client.new ENV['api_key']
    #message_params = {:from    => @user.email,
    #                  :to      => ENV['gmail_username'],
    #                  :subject => 'Sample Mail using Mailgun API',
    #                  :text    => 'This mail is sent using Mailgun API via mailgun-ruby'}
    #mg_client.send_message ENV['domain'], message_params
  end
  end
