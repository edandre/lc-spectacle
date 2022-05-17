package com.lc.spectacle.di

import com.lc.spectacle.core.Constants
import com.lc.spectacle.features.auth.data.remote.firebase.FirebaseAuthenticator
import com.lc.spectacle.features.auth.data.repository.AuthenticationRepositoryImpl
import com.lc.spectacle.features.auth.domain.repository.IAuthenticationRepository
import com.lc.spectacle.features.auth.domain.use_case.login.LoginUseCase
import com.lc.spectacle.features.auth.domain.use_case.register.RegisterUseCase
import com.lc.spectacle.features.movie_library.data.remote.MoviesDatabaseApi
import com.lc.spectacle.features.movie_library.data.repository.MoviesRepositoryImpl
import com.lc.spectacle.features.movie_library.domain.repository.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    // Auth feature
    @Provides
    @Singleton
    fun providesFirebaseAuthenticator(): FirebaseAuthenticator {
        return FirebaseAuthenticator()
    }

    @Provides
    @Singleton
    fun providesAuthorizationRepository(firebaseAuthenticator: FirebaseAuthenticator): IAuthenticationRepository {
        return AuthenticationRepositoryImpl(firebaseAuthenticator)
    }

    // Use Cases
    @Provides
    @Singleton
    fun providesRegisterUseCase(authenticationRepository: IAuthenticationRepository): RegisterUseCase {
        return RegisterUseCase(authenticationRepository)
    }

    @Singleton
    @Provides
    fun providesDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(authenticationRepository: IAuthenticationRepository): LoginUseCase {
        return LoginUseCase(authenticationRepository)
    }

    // Movies Library Feature
    @Provides
    @Singleton
    fun providesMoviesDatabaseApi(): MoviesDatabaseApi {
        return Retrofit.Builder()
            .baseUrl(Constants.ApiBaseUrl)
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesDatabaseApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(moviesDatabaseApi: MoviesDatabaseApi): IMoviesRepository {
        return MoviesRepositoryImpl(moviesDatabaseApi)
    }
}