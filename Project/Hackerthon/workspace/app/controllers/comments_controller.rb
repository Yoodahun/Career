class CommentsController < ApplicationController
  before_action :set_comment, only: [:destroy]



  # POST /comments
  # POST /comments.json
  def create
    @comment = Comment.new(comment_params)
    @comment.save!

    
    redirect_to post_path(params[:post_id])
  end

  

  # DELETE /comments/1
  # DELETE /comments/1.json
  def destroy
    @comment.destroy
  
    redirect_to post_path(params[:post_id])
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_comment
      @comment = Comment.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def comment_params
      params.require(:comment).permit(:content, :user_id, :post_id)
    end
end
