let currentPage = 0;
const pageSize = 5;

$(document).ready(function () {
    loadPosts();

    // Gửi form tìm kiếm
    $('#search-form').submit(function (event) {
        event.preventDefault();
        var keyword = $('#keyword').val();
        $.ajax({
            type: 'GET',
            url: '/api/posts/search',
            data: { keyword: keyword },
            success: function (data) {
                var content = '';
                $.each(data, function (i, p) {
                    content += '<div class="post">' +
                        '<h3>' + p.title + '</h3>' +
                        '<p>' + p.content + '</p>' +
                        '</div>';
                });
                $('#post-list').html(content);
                $('#load-more').hide();
            }
        });
    });

    // Sự kiện nút tải thêm
    $('#load-more').click(function () {
        loadPosts();
    });
});

function loadPosts() {
    $.ajax({
        type: 'GET',
        url: '/api/posts',
        data: { page: currentPage, size: pageSize },
        success: function (data) {
            var content = '';
            $.each(data, function (i, p) {
                content += '<div class="post">' +
                    '<h3>' + p.title + '</h3>' +
                    '<p>' + p.content + '</p>' +
                    '</div>';
            });
            $('#post-list').append(content);
            currentPage++;
        }
    });
}
