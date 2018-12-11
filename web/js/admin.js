
$('.likes').on('click', function (e) {
    var imageInfo = getInfoAboutImage(this);
    changeTotalLike(imageInfo);
});

function getInfoAboutImage(value) {
    var child = value.children;

    var result = {};

    for (var i = 0; i < child.length; i++) {
        var obj = child[i];

        if (obj.tagName === 'INPUT') {
            result.imageId = obj.value;
        } else if (obj.tagName === 'SPAN') {
            result.spanTag = obj;
        }
    }

    return result;
}

function changeTotalLike(value) {
    $.ajax({
        type: 'POST',
        url:  '/pretty_image-0.1/image/like',
        data: {'imageId':value.imageId},
        success: function (data) {
            console.log(data);
            var likes = Number(value.spanTag.innerHTML);
            if (data === "added") {
                value.spanTag.innerHTML = likes + 1;
            } else if (data === "deleted") {
                value.spanTag.innerHTML = likes - 1;
            }
        }
    })
}