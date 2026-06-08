package com.mountainrescue.member.service;

import com.mountainrescue.member.controller.dto.JoinRequest;

public interface MemberService {

    String join(JoinRequest joinRequest);
}
