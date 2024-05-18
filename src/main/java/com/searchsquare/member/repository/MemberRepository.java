package com.searchsquare.member.repository;

import com.searchsquare.member.service.dto.MemberDto;
import com.searchsquare.member.service.dto.SearchMemberCond;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    void save(MemberDto member);

    MemberDto findExistingMember(SearchMemberCond cond);
}
