const API = "http://localhost:3000/posts";

function loadPosts() {
    fetch(API)
        .then(res => res.json())
        .then(posts => {
            const ul = document.getElementById("postList");
            ul.innerHTML = "";

            posts.forEach(p => {
                const li = document.createElement("li");
                li.textContent = `${p.id} - ${p.title}`;

                if (p.isDeleted) {
                    li.classList.add("deleted");
                }

                if (!p.isDeleted) {
                    const btn = document.createElement("button");
                    btn.textContent = "Xoá";
                    btn.onclick = () => softDeletePost(p);
                    li.appendChild(btn);
                }

                ul.appendChild(li);
            });
        });
}

function createPost() {
    const title = document.getElementById("title").value;

    fetch(API)
        .then(res => res.json())
        .then(posts => {
            const maxId = posts.length === 0
                ? 0
                : Math.max(...posts.map(p => Number(p.id)));

            const newPost = {
                id: (maxId + 1).toString(),
                title,
                views: 0,
                isDeleted: false
            };

            return fetch(API, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(newPost)
            });
        })
        .then(loadPosts);
}

function softDeletePost(post) {
    post.isDeleted = true;

    fetch(`${API}/${post.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(post)
    }).then(loadPosts);
}

loadPosts();

const COMMENT_API = "http://localhost:3000/comments";

function createComment(postId, text) {
    fetch(COMMENT_API)
        .then(res => res.json())
        .then(comments => {
            const maxId = comments.length === 0
                ? 0
                : Math.max(...comments.map(c => Number(c.id)));

            const newComment = {
                id: (maxId + 1).toString(),
                postId,
                text,
                isDeleted: false
            };

            return fetch(COMMENT_API, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(newComment)
            });
        });
}

function softDeleteComment(comment) {
    comment.isDeleted = true;

    fetch(`${COMMENT_API}/${comment.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(comment)
    });
}
