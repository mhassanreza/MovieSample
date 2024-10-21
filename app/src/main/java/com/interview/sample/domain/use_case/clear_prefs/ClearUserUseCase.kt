package com.interview.sample.domain.use_case.clear_prefs

import com.interview.sample.domain.repository.ClearPreferenceRepository
import javax.inject.Inject

class ClearUserUseCase @Inject constructor(
    private val clearPreferenceRepository: ClearPreferenceRepository
) {
    suspend operator fun invoke() {
        return clearPreferenceRepository.clearUser()
    }
}