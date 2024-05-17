package com.searchsquare.member.repository;

import com.searchsquare.member.service.dto.ExistMemberSearch;
import com.searchsquare.member.service.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    void save(MemberDto member);

    MemberDto findExistingMember(ExistMemberSearch cond);
}
