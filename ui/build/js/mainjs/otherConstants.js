define(['angular'],
		function(angular) {
		    return {
		    	seq_pattern:"/^((?!.*abcde|bcdef|cdefg|defgh|efghi|fghij|ghijl|hijkl|ijklm|jklmn|klmno|lmnop|mnopq|nopqr|opqrs|pqrst|qrstu|rstuv|stuvw|tuvwx|uvwxy|vwxyz|qwer|wert|erty|rtyu|tyui|yuio|uiop|asdf|sdfg|dfgh|fghj|ghjk|hjkl|zxcv|xcvb|cvbn|vbnm)([a-zA-Z])\2?(?!\2{2}))+$/",
		    	num: new RegExp("(^0$)|(^[1-9]\d{0,9}$)")
		    }
		}
)
