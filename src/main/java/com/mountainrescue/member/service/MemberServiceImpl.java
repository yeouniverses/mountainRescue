package com.mountainrescue.member.service;

import com.mountainrescue.member.controller.dto.JoinRequest;
import com.mountainrescue.member.repository.MemberRepository;
import com.mountainrescue.member.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String join(JoinRequest joinRequest){
        Member member = Member.builder()
                .id(joinRequest.getId())
                .name(joinRequest.getName())
                .build();
        memberRepository.save(member);

        return "success";
    }
}
