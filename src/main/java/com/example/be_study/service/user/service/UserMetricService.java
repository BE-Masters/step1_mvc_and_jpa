package com.example.be_study.service.user.service;

import com.example.be_study.service.user.domain.User;
import com.example.be_study.service.user.domain.UserMetric;
import com.example.be_study.service.user.enums.DeviceType;
import com.example.be_study.service.user.repository.UserMetricRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserMetricService {
    public UserMetricRepository userMetricRepository;

    public UserMetricService(UserMetricRepository userMetricRepository) {
        this.userMetricRepository = userMetricRepository;
    }


    @Transactional(readOnly = false)
    public void createVeryManyUser() {

        userMetricRepository.saveAllUserBulkMode();
    }

    public Page<UserMetric> getUserMetricPage(Pageable pageable){
        return userMetricRepository.findAll(pageable);
    }

    //기존 쿼리에서 통계를 내서 보여주는 방식에서 테이블에서 전체 나이 데이터를 조회후 서버에서 통계내는 방식으로 변경
    @Transactional(readOnly = true)
    public LinkedHashMap<String, Integer> getUserAgeGroup(){
        LinkedHashMap<String, Integer> group = new LinkedHashMap<>();
        List<Short> ages = new ArrayList<>();

        for (int i = 2; i<10; i++){// 나이 통계 계산을 위한 그룹 생성
            group.put(Integer.toString(i*10)+" 대",0);
        }

        ages = userMetricRepository.getUserAgeGroup(); //나이 조회

        for(int i  = 0; i<ages.size();i++){
            int num = ages.get(i); //나이 가져오기
            String key = Integer.toString((num / 10) * 10) + " 대"; //나이 그룹을 key로 설정
            int cnt = group.get(key)+1; //해당 나이대를 개수를 가져와 +1
            group.put(key,cnt);
        }

        return group;
    }


    //groupingby은 특정속성값으로 그룹을 만드는것으로 UserMetric dto에는 userId, age, deviceType중 age, deviceType이 가능, id의 경우는 key값으로 적절하지 않음
    public Map<Short, List<UserMetric>> matricMapGroup() {
        List<UserMetric> List = userMetricRepository.findTop100ByOrderByUserIdDesc();
        Map<Short, List<UserMetric>> matricMapGroup = List.stream().collect(Collectors.groupingBy(UserMetric::getAge)); //나이로 그룹을 만들경우
//        Map<DeviceType, List<UserMetric>> matricMapGroup = List.stream().collect(Collectors.groupingBy(UserMetric::getDeviceType)); //Device Type으로 그룹을 만들경우
        return matricMapGroup;
    }

    //toMap은 조회결과List를 Map으로 변환할때 사용
    public Map<Long, UserMetric> matricToMap() {
        List<UserMetric> List = userMetricRepository.findTop100ByOrderByUserIdDesc();
        Map<Long, UserMetric> matricToMap = List.stream().collect(Collectors.toMap(UserMetric::getUserId,Function.identity()));
        return matricToMap;
    }

}
