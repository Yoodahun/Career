class MypageController < ApplicationController
    
    def mypage
    #로그인 화면만 네비바가 표시안되게 설정
    @week = 7
    @row = 5
    @i = 1
    @j = 1

    if user_signed_in? #유저가 로그인했으면?
      @user_information = ApplicationForm.find_by_user_id(current_user.id) #현재 로그인한 유저의 아이디값으로 현재 유저가 작성한 폼을 가져오기
      
      @matched = false 
      @match_1 = []
      @match_2 = []
      @match_3 = false
      @match_4 = false
      @match_5 = false
      #match_5는 조건문을 위한 임시 변수~
      
      #match 변수는 개발자가 쓰는 변수이다.
      #match같은 경우는 앞 뒤로 모두 매칭 되었을 때만 참 이지만, 유저 입장에서는 하나라도 매치가 되면 참이 나와야한다.
      #따라서 matched라는 변수를 두어, 둘 중 하나만 되면 매치 됫다고 띄워주자.
      
      

      if (ApplicationForm.find_by_user_id(current_user.id) != nil) #폼을 가져왔는데 nil값이 아닐때. 폼이 존재할때.

        user = ApplicationForm.find_by_user_id(current_user.id) #위에랑 중첩
        #where로 받아올때는 배열로 받아와짐.
        user.match = false #첫 매치값은 폴스

        # if (user.match_head && user.match_tail) #앞사람 뒷사람 둘다 있을 때는
        #     user.match = true
        #     user.save!
        # end


        #
        if user.match_head === false #앞사람이 없을때
          #
          @match_1 = ApplicationForm.where(area: user.area, hotel: user.hotel, peopleNumber: user.peopleNumber,
                                           check_out: user.check_in, match_tail: user.match_head)
          #checkout 이 checkin 하고 같은 애
          #앞
        end
        #
        if user.match_tail === false #뒷사람이 없을때 (match_head도 false인 사람을 찾아야댐)
          #
          @match_2 = ApplicationForm.where(area: user.area, hotel: user.hotel, peopleNumber: user.peopleNumber,
                                           check_in: user.check_out, match_head: user.match_tail)
          #checkin 이 checkout과 같은 애
          #뒤
        end

        if @match_1 != [] #빈배열이 아니라면 내용이 있음
          @match_3 = true #매치3 트루
        end
        #사용자 체크인하고 맞는 체크아웃이 없는떄
        if @match_2 != []
          @match_4 = true #빈배열이 아니면 매치4 트루
        end
        #사용자 체크아웃하고 맞는 체크 인이 없을때
        
        if (@match_3 || @match_4)
          @match_5 = true
        #매치 됬을 때
        end

        if @match_5 #앞 뒤중 하나라도 트루일때. 매치일때
          #
          # user.match = true
          #
            if @match_3 == true #앞사람이 있을 때


            #사용자의 꼬리는 트루고 대가리는 폴스
            #매치 요청자는 꼬리는 폴스고 대가리는 트로
            #
            user.user_id_head = @match_1[0].user_id
            #앞사람의 아이디값을 "내 앞사람이 누군지?" 확인하기위해 user_id_head에 저장
            #
            user.match_head = true
            #사용자 꼬리를 true로 바꾸자
            #@user_information.match_head = false
            #
            user.save!
            #@match_1[0].match = true
            #
            #사용자의 매치는 이미 위에서 true로 바꾸어 주었다.
            #따라서 같이 매치된 친구의 매치값도 true로 바꾸어 주어, 중복되는 부킹을 막는다.
            #
            @match_1[0].user_id_tail = user.user_id
            #나의 아이디값을 나와 매칭된 앞사람의 뒷사람아이디(user_id_tail)에 저장
            #
            #@match_1[0].match_tail = false
            @match_1[0].match_tail = true
            #신청자의 대가리를 true

            @match_1[0].save!
            #사용자 체크인하고 맞는 체크아웃이 있는 경우, 그 친구 가져오기
            end

            if @match_4 == true #나의 뒷사람의 정보가 있을때

            #사용자의 꼬리를 폴스로
            #대가리는 트루로
            #
            user.user_id_tail = @match_2[0].user_id
            #나의 "뒷사람아이디(user_id_tail)"에 뒷사람의 id를 저장
            #
            #@user_information.match_tail = false
            user.match_tail = true
            #
            user.save!
            # @match_2[0].match = true
            @match_2[0].user_id_head = user.user_id
            #
            @match_2[0].match_head = true
            #@match_1[0].match_head = false

            @match_2[0].save!
            #사용자 체크아웃하고 맞는 체크인이 있는 경우, 그 친구 가져오기
            end
            
        end
        
        if (user.match_head && user.match_tail)
                user.match = true
                user.save!
        end
                
        if (user.match_head||user.match_tail)
                  @matched = true
        end
      end
      
      respond_to do |format|
        format.html {render :layout => 'loginview'}
      end

    else # 유저가 로그인 안해있으면?

        respond_to do |format|
        format.html {render :layout => 'loginview'}
    end

    end #else end
    
    end

  #액션 end
end
