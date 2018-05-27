// including plugins
var gulp = require('gulp'),
uglify = require("gulp-uglify"),
minifyHtml = require("gulp-minify-html"),
minifyCss = require("gulp-minify-css"),
mainBowerFiles = require('main-bower-files'),
notifier = require('node-notifier'),
del = require('del'),
jsonminify = require('gulp-jsonminify');
// task
gulp.task('all', function () {
    return gulp.src('build/**')
        .pipe(gulp.dest('_build/kyc-zm/'));
});
gulp.task('clean:libs',['all'], function() {
    return del(['_build/kyc-zm/libs/vendor/'])
});
gulp.task('libs',['clean:libs','all'], function () {
    // return gulp.src('bower_components/**/*.js')
    //     .pipe(gulp.dest('_build/kyc-zm/libs/vendor/'));
    return gulp.src(mainBowerFiles('**/*',{includeDev:true}), { base: 'bower_components' })
        .pipe(gulp.dest('_build/kyc-zm/libs/vendor/'))
});
gulp.task('js',['all'], function () {
    gulp.src('build/js/**/*.js')
    .pipe(uglify())
        .pipe(gulp.dest('_build/kyc-zm/js/'));
});
gulp.task('css',['all'], function () {
    return gulp.src('build/assets/**/*.css')
        .pipe(minifyCss())
        .pipe(gulp.dest('_build/kyc-zm/assets/'));
});
gulp.task('json',['all'], function () {
    return gulp.src('build/assets/**/*.json')
        .pipe(jsonminify())
        .pipe(gulp.dest('_build/kyc-zm/assets/'));
});
gulp.task('html',['all'], function () {    
    return gulp.src('build/templates/**/*.html')
        .pipe(minifyHtml())
        .pipe(gulp.dest('_build/kyc-zm/templates/'));
    return gulp.src('build/templates/*.html')
        .pipe(minifyHtml())
        .pipe(gulp.dest('_build/kyc-zm/templates/'));
});
gulp.task('index',['all'], function () {
    return gulp.src('build/*.html')
        .pipe(minifyHtml())
        .pipe(gulp.dest('_build/kyc-zm/'));
});
gulp.task('build', ['all','clean:libs','libs','css','js','json','html','index'], function () {
    notifier.notify({
            title : "Success",
            message :"Build created successfully",
            time : 2000
        });
});
// gulp.task('js',['all'], function () {
//     gulp.src('build/js/mainjs/routes.js')
//     .pipe(uglify().on('error', function(e){
//             console.log(e);
//          }))
//         .pipe(gulp.dest('_build/kyc-zm/js/'));
// });
// gulp.task('build', ['all','js'], function () {
// });

