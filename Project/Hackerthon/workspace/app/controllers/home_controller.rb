class HomeController < ApplicationController


  # def login
  #   #로그인 화면만 네비바가 표시안되게 설정

  #   if user_signed_in? #유저가 로그인했으면?
      # @user_information = ApplicationForm.find_by_user_id(current_user.id) #현재 로그인한 유저의 아이디값으로 현재 유저가 작성한 폼을 가져오기
  #     @matched = false 
  #     @match_1 = []
  #     @match_2 = []
  #     @match_3 = false
  #     @match_4 = false
      
      
      

  #     if (ApplicationForm.find_by_user_id(current_user.id) != nil) #폼을 가져왔는데 nil값이 아닐때. 폼이 존재할때.

  #       user = ApplicationForm.find_by_user_id(current_user.id) #위에랑 중첩
  #       #where로 받아올때는 배열로 받아와짐.
  #       user.match = false #첫 매치값은 폴스

  #       if (user.match_head && user.match_tail) #앞사람 뒷사람 둘다 있을 때는
  #           user.match = true
  #           @matched = true #이것은 뷰에서 쓰기위한 것
  #           user.save!
  #       end


  #       #
  #       if user.match_head === false #앞사람이 없을때
  #         #
  #         @match_1 = ApplicationForm.where(area: user.area, hotel: user.hotel, peopleNumber: user.peopleNumber,
  #                                         check_out: user.check_in, match_tail: user.match_head)
  #         #match: user.match)
  #         #checkout 이 checkin 하고 같은 애
  #         #앞
  #       end
  #       #
  #       if user.match_tail === false #뒷사람이 없을때 (match_head도 false인 사람을 찾아야댐)
  #         #
  #         @match_2 = ApplicationForm.where(area: user.area, hotel: user.hotel, peopleNumber: user.peopleNumber,
  #                                         check_in: user.check_out, match_head: user.match_tail)
  #         #checkin 이 checkout과 같은 애
  #         #뒤
  #       end

  #       if @match_1 != [] #빈배열이 아니라면 내용이 있음
  #         @match_3 = true #매치3 트루
  #       end
  #       #사용자 체크인하고 맞는 체크아웃이 없는떄
  #       if @match_2 != []
  #         @match_4 = true #빈배열이 아니면 매치4 트루
  #       end
  #       #사용자 체크아웃하고 맞는 체크 인이 없을때
  #       if (@match_3 || @match_4)
  #         @match_5 = true
  #       #매치 됬을 때
  #       end


  #       if @match_5 #앞 뒤중 하나라도 트루일때. 매치일때
  #         #
  #         # user.match = true
  #         #
  #         if @match_3 == true #앞사람이 있을 때


  #           #사용자의 꼬리는 트루고 대가리는 폴스
  #           #매치 요청자는 꼬리는 폴스고 대가리는 트로
  #           #
  #           user.user_id_head = @match_1[0].user_id
  #           #앞사람의 아이디값을 "내 앞사람이 누군지?" 확인하기위해 user_id_head에 저장
  #           #
  #           user.match_head = true
  #           #사용자 꼬리를 true로 바꾸자
  #           #@user_information.match_head = false
  #           #
  #           user.save!
  #           #@match_1[0].match = true
  #           #
  #           #사용자의 매치는 이미 위에서 true로 바꾸어 주었다.
  #           #따라서 같이 매치된 친구의 매치값도 true로 바꾸어 주어, 중복되는 부킹을 막는다.
  #           #
  #           @match_1[0].user_id_tail = user.user_id
  #           #나의 아이디값을 나와 매칭된 앞사람의 뒷사람아이디(user_id_tail)에 저장
  #           #
  #           #@match_1[0].match_tail = false
  #           @match_1[0].match_tail = true
  #           #신청자의 대가리를 true

  #           @match_1[0].save!
  #           #사용자 체크인하고 맞는 체크아웃이 있는 경우, 그 친구 가져오기
  #         end

  #         if @match_4 == true #나의 뒷사람의 정보가 있을때

  #           #사용자의 꼬리를 폴스로
  #           #대가리는 트루로
  #           #
  #           user.user_id_tail = @match_2[0].user_id
  #           #나의 "뒷사람아이디(user_id_tail)"에 뒷사람의 id를 저장
  #           #
  #           #@user_information.match_tail = false
  #           user.match_tail = true
  #           #
  #           user.save!
  #           # @match_2[0].match = true
  #           @match_2[0].user_id_head = user.user_id
  #           #
  #           @match_2[0].match_head = true
  #           #@match_1[0].match_head = false

  #           @match_2[0].save!
  #           #사용자 체크아웃하고 맞는 체크인이 있는 경우, 그 친구 가져오기
  #         end

  #         if (user.match_head && user.match_tail)
  #           user.match = true
  #           @matched = true
  #           user.save!
  #         end


  #       end

  #     end

  #     respond_to do |format|
  #       format.html {render :layout => 'loginview'}
  #     end

  #   else # 유저가 로그인 안해있으면?

  #     respond_to do |format|
  #       format.html {render :layout => 'loginview'}
  #     end

  #   end #else end
  # end

  # #액션 end


  def map #맵
    require 'rubygems'
    require 'json'
    @file = File.join(Rails.root, 'app', 'assets', 'json', '0.json')
    @data = File.read(@file).to_s

  end

  def application_form

  end

  def create_form #폼에 데이터 입력하는 메소드
    if current_user.application_form #만약 폼이 잇으면 다시 맵으로 돌아가게하기
      redirect_to '/map'
    else
      #디비에 들어가는 키 와 밸류의 쌍
      @applicationForm = ApplicationForm.new
      @applicationForm.user_id = current_user.id
      @applicationForm.area = params[:area]
      @applicationForm.hotel = params[:hotel]
      @applicationForm.email = params[:email]
      @applicationForm.peopleNumber = params[:job]
      @applicationForm.representiveName = params[:name]
      @applicationForm.sex = params[:gender]
      @applicationForm.check_in = params[:check_in]
      @applicationForm.check_out = params[:check_out]
      @applicationForm.match = false
      @applicationForm.match_tail = false
      @applicationForm.match_head = false

      @applicationForm.save

      redirect_to '/login'
    end
  end

#   def match


#     user = ApplicationForm.where(id: current_user)
#     #유저 정보를 받아오자는 건가?
#     #유저 정보를 받아왔다는 가정하에, 그럼 객체 처럼 올텐데
#     #객체처럼 오는 것이 아니라 배열로 옮. 사용할때는 @user으로 사용해야.


#     #@match = ApplicationForm.where(check_out: @user.check_in)
#     #여기에서 위편에서 불러온 객체의 check_in 이라는 필드 값과 check_out 이 같으면 match로 가져오자는 거 같음
#     # @match = ApplicationForm.where(check_out: @user.check_in, area: @user.area, hotel: @user.hotel, peopleNumber: @user.peopleNumber)
#     #이렇게 하면 rails c 로 봣을 때는 여러개의 조건을 줘서 만족하는 것을 출력할 수 있었는데
#     #db에 form 이 하나 밖에 없어서 여러 실험을 못해봄
#     #현재 지도가 제대로 클릭되지 않아서 신청서를 작성할 수가 없음
#     match_1 = ApplicationForm.where(area: user.area, hotel: user.hotel, peopleNumber: user.peopleNumber,
#                                     check_in: user.check_out)
#     match_2 = ApplicationForm.where(area: user.area, hotel: user.hotel, peopleNumber: user.peopleNumber,
#                                     check_out: user.check_in)
#     #현재 유저의 체크인, 체크아웃시간이 다른 사람들의 체크아웃, 체크인과 겹치는 것이 있는지 찾음
#     #단순히 시간으로만 찾는다면 많은 사람이 걸릴 수 있으나 지역,호텔,인원수까지 검사하므로 겹치는 수가 많아지는 것은
#     #확률이 낮아짐.
#     #앞뒤로 묵을 사람들과 새로 매칭할지는 모델을 하나 더 만들어야겠으나 여기서는 배제하기로함.
#     if((match_1 || match_2) != [])
#       $matched = true

#     else
#       $matched = false
#     end
#     #매치1과 매치2중 하나라도 매칭된다면 매치된 것임.
#     #둘다 빈 배열이라면 매칭중이라는 것.
#     #초기값이 nil이므로 nil이라면 신청자체를 안한것.
#   end

#   redirect_to '/login'
end


