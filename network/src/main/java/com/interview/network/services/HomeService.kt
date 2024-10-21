package com.interview.network.services

import android.content.Context
import com.interview.data.remote.home.RemoteMovieModel
import com.interview.data.remote.home.mapToHomeMovieDto
import com.interview.domain.home.HomeMovieDto
import com.interview.network.ApiService
import com.interview.network.utils.Resource
import com.interview.network.utils.ResourceFilterUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeService @Inject constructor(
    @ApplicationContext val context: Context, private val apiService: ApiService
) {
    fun getHomeMovieListingData(): Resource<List<HomeMovieDto>> {
        return try {
            val response = mapToHomeMovieDto(moviesList)
            Resource.Success(response)
        } catch (e: Exception) {
            // Catch network exceptions or unexpected errors
            Resource.Error(ResourceFilterUtil.filterResource(e))
        }
    }

    private val moviesList = listOf(
        RemoteMovieModel(
            id = "1",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Jubilee",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/9e39552f26c52760492026c8f36116928ac3a2b3bb1bb5ab0e187aac69361fab._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/1ec963e832611fd653f827ba64eb4ab44034fa88a25b014b7a7f1844880937c4._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "2",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "The Power",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/d5f46c87921f6a416c02a8d99200ec6269f7473d744fadd701a1e9f2fd43509c._UR200,300_.png",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/0345e3278ea8e894adc93093199a0c2b83078e54ba703eb37074a68ab9edde08._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "3",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Class of '07",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/e289a847c276200fa731a65eba22da89ecf1a093fc0b03620207c2a72732f9ff._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/978fe6c6977ec35b6233a366362af570da7a9272e79fdbe5679c3b983e30f800._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "4",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "DOM",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/015b102c8ab8d56440b1a494cc1413f48fc6a61730994bc77383476dd8c4047b._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/954e29100302a537de68af075e1204f858c04c03bc1a7b2bc1a20de9873bdd21._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "5",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Happy Family Conditions Apply",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/3041e47a54120640eb58dc88195ebcd8d2962782672a8b6979cbae4326737acd._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/9c4d3161c6c4772d9dd7eb650e2ae5a013c90079d880686929189110105616e4._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "6",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Farzi",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/c3409cb9e64b931584b713eb4911ef9bea53ef4fa2ddd83224e07dd42c3c88ef._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/8aed532f0875925f72c4012aab688ed409773ecbfb3b18e1a39cd9ad1a4dd485._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "7",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Mirzapur",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/5ddf17c0744f2d5d0a792545d380899eee506b5d99954a4ef000ee18840f81b9._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/2f5c9e434c0e6517ef9d8d0580134f6afde4890cdf57df3a870e8d69a46afb02._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "8",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "The Family Man",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/3fada916232eb9d5a8cd1ec981cbab1e7baa791546cb7180056c4b36759c5014._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/9c4d3161c6c4772d9dd7eb650e2ae5a013c90079d880686929189110105616e4._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "9",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "The Boys",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/1a7c85becdd48b307cdecfb96e8e43690095bf30cf56d0cb7c7d542f1aa4b530._UR200,300_.png",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/a008b073c68fc474ed6397e84cb4436c5e26d3bfdbcc6fcc16e122c37351c944._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "10",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Tom Clancy's Jack Ryan",
            thumbnail = "https://images-eu.ssl-images-amazon.com/images/S/atv-aps-images/encoded/JKRY_S3/IN/en_IN/POSTER_ART/CLEAN/HolidayArt2._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/fba53fc76ea725769451e68c1fdf7b44058eb5ed9ab806335c0bf6854dac9bcc._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "11",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Reacher",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/9c4c7c4270b676d009a9a0a395334f678869df4e227ad3e98b8f5d3161c67dad._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/56d391fc82789f8a0f04649842743bc853bebf2619d45d85e3486fb25a8f2d66._UR1920,1080_.png"
        ), RemoteMovieModel(
            id = "12",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            title = "Inside Edge",
            thumbnail = "https://m.media-amazon.com/images/S/pv-target-images/e8571c61ffcb6c287ed54fa3d0655e6afee21cbbbd723c75f5df5da17383c9f7._UR200,300_.jpg",
            banner = "https://m.media-amazon.com/images/S/pv-target-images/846480559abe60b7718709966edb2bf005d001ce65de436aa607a2affd90d403._UR1920,1080_.jpg"
        ), RemoteMovieModel(
            id = "13",
            title = "Thor the dark world",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/thorthedarkworld_lob_crd_02_1.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/thor_tdw.jpg"
        ),

        RemoteMovieModel(
            id = "14",
            title = "guardians of the galaxy",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/guardiansofthegalaxy_lob_crd_03.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/gog_2.jpg"
        ), RemoteMovieModel(
            id = "15",
            title = "Avengers Age Of Ultron",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/avengersageofultron_lob_crd_03.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/age_of_ultron.jpg"
        ), RemoteMovieModel(
            id = "16",
            title = "AntMan",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/ant-man_lob_crd_01_8.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/ant_1.jpg"
        ), RemoteMovieModel(
            id = "17",
            title = "Doctor Strange",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/doctorstrange_lob_crd_01_6.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/doctor_strange.jpg"
        ), RemoteMovieModel(
            id = "18",
            title = "guardians of the galaxy VOL 2",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/guardiansofthegalaxyvol.2_lob_crd_01.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/gog_2.jpg"
        ), RemoteMovieModel(
            id = "19",
            title = "SpiderMan - Homecoming ",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/spider-manhomecoming_lob_crd_01_3.jpg",
            banner = "http://pixeldev.in/webservices/movie_app/movie_admin/banner_images/home.jpg"
        ), RemoteMovieModel(
            id = "20",
            title = "IronMan",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/ironman_lob_crd_01_3.jpg",
            banner = "https://wallpapercave.com/wp/wp3405080.jpg"
        ), RemoteMovieModel(
            id = "21",
            title = "Incredible Hulk",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/theincrediblehulk_lob_crd_01_2.jpg",
            banner = "https://static1.colliderimages.com/wordpress/wp-content/uploads/2022/03/The-Incredible-Hulk-2.jpg"
        ), RemoteMovieModel(
            id = "22",
            title = "Iron Man 2",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/ironman2_lob_crd_01_3.jpg",
            banner = "http://wallpaperstock.net/iron-man-2-poster%2C---movies_wallpapers_57551_852x480.jpg"
        ), RemoteMovieModel(
            id = "23",
            title = "Thor",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/thor_lob_crd_01.jpg",
            banner = "https://wallpaperaccess.com/full/645142.jpg"
        ), RemoteMovieModel(
            id = "24",
            title = "Iron Man 3",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/ironman3_lob_crd_01_10.jpg",
            banner = "https://img1.hotstarext.com/image/upload/f_auto,t_hcdl/sources/r1/cms/prod/4294/674294-h"
        ), RemoteMovieModel(
            id = "25",
            title = "Joker",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/joker.jpg",
            banner = "https://www.joker.movie/images/share.jpg"
        ), RemoteMovieModel(
            id = "26",
            title = "Wonder Woman (2017)",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/ww2017.jpg",
            banner = "http://media.comicbook.com/2017/05/screen-shot-2017-05-22-at-11-17-45-am-998115.png"
        ), RemoteMovieModel(
            id = "27",
            title = "AquaMan",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/aquaman.jpg",
            banner = "https://pbs.twimg.com/media/Ds6O-u4U4AECCXJ?format=jpg&title=900x900"
        ), RemoteMovieModel(
            id = "28",
            title = "Wonder Woman 1984 (2020)",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/ww84.jpg",
            banner = "https://heroichollywood.com/wp-content/uploads/2020/02/Gal_Gadot_Wonder_Woman.jpg"
        ), RemoteMovieModel(
            id = "29",
            title = "Batman Begin (2005)",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/batman_2005.jpg",
            banner = "https://i.ytimg.com/vi/NJc6p8huN5Q/maxresdefault.jpg"
        ), RemoteMovieModel(
            id = "30",
            title = "The Dark Knight",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/batman_2008.jpg",
            banner = "https://cdn.flickeringmyth.com/wp-content/uploads/2020/02/dark-knight-poster-banner-600x319.jpg"
        ), RemoteMovieModel(
            id = "31",
            title = "Man Of Steel",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/manofsteel_thumb_192x291_52ab977d243284.01494825.jpg",
            banner = "https://static1.colliderimages.com/wordpress/wp-content/uploads/2022/03/The-Incredible-Hulk-2.jpg"
        ), RemoteMovieModel(
            id = "32",
            title = "venom",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/venom.png",
            banner = ""
        ), RemoteMovieModel(
            id = "33",
            title = "spectre",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/spectre.jpg",
            banner = ""
        ), RemoteMovieModel(
            id = "34",
            title = "TENET",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/tenet.jpg",
            banner = ""
        ),

        RemoteMovieModel(
            id = "35",
            title = "Jumanji-Welcome to the Jungle",
            trailer_url = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_video/nature_1.mp4",
            thumbnail = "http://pixeldev.in/webservices/movie_app/movie_admin/movie_thumbnail/Jumanji_Welcome_to_the_Jungle.png",
            banner = ""
        )
    )
}