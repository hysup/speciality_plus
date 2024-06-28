package com.example.kotlin25.config.sercurity

import org.springframework.security.core.GrantedAuthority

data class UserPrincipal(
    val id: Long,
    val nickname: String,
    val authorities: Collection<GrantedAuthority>
){
    constructor (id: Long,nickname: String) : this(id, nickname, ArrayList())
}

