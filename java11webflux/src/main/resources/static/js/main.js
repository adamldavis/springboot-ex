
let errorHandler = (err) => { alert("Sorry, there was a problem. " + err); console.log(err) }

let HC = {
    loadCourses: function() {
        jQuery.ajax({method: 'get', url: '/api/courses'}).done(
            function(data) {
                let list = data;
                let ul = jQuery('<ul class="courses btn-group"></ul>');
                list.forEach((crs) => {
                    ul.append('<li class="btn-link" onclick="HC.loadCourse(\''+crs.id+'\'); return false">'
                        + crs.name + ': <i>' + HC.toDollars(crs.price) + '</i></li>')
                });
                jQuery('#content').html(ul);
            }
        ).fail( errorHandler );
    },
    toDollars: function(price) {
        return '$' + (price/ 100);
    },
    loadCourse: function() { /*TODO*/ },
    postCourse: function() {
        let name = jQuery('#name').val();
        let price = jQuery('#price').val();
        jQuery.ajax({method: 'post', url: '/api/course/', data: {name: name, price: price}}).done(
            function(course) {
                console.log("data=" + course);
                HC.course = course;
                let segments = course.segments;
                let ul = jQuery('<ul class="segments btn-group"></ul>');
                segments.forEach((segment) => {
                    ul.append('<li class="btn-link" onclick="HC.loadSegment(' + segment.id + ')">'
                        + segment.name + '</li>')
                });
                jQuery('h1').text(course.name);
                jQuery('#content').html(ul);
            }
        ).fail( errorHandler );
    }
}

