/*
 * Copyright 2021 Sergio Belda Galbis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.usecase

import com.sergiobelda.androidtodometer.model.Project
import com.sergiobelda.androidtodometer.preferences.UserPreferencesRepository
import com.sergiobelda.androidtodometer.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetProjectSelectedUseCase(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val projectRepository: ProjectRepository
) {
    private val projectSelected: Flow<Int> = userPreferencesRepository.projectSelected()

    operator fun invoke(): Flow<Project?> = combine(
        projectRepository.projects,
        projectSelected
    ) { projects, projectSelected ->
        return@combine projects.firstOrNull { it.projectId == projectSelected }
    }
}