class MessageController < ApplicationController

def new
  @message = Message.new
end

def create
  @message = Message.new message_params

  if @message.valid?
    MessageMailer.contact(@message).deliver_now
    redirect_to message_clear_path
    flash[:notice] = "We have received your message and will be in touch soon!"
   else
     flash[:notice] = "There was an error sending your message. Please try again."
     render :new
  end
end

def clear
end


private

def message_params
  params.require(:message).permit(:title, :email, :message)
end

end
