

//---CAROUSEL FUNCTIONS---//


let allClusterIndexes = []

function showStartingSlides() {	
	const slideIndex = document.getElementsByClassName("clusterCard");
	for(let i = 0; i<slideIndex.length; i++) {
		allClusterIndexes.push(1);
		showSlides(1, i);
	}
}

function plusSlides(increaseAmount, oneClusterIndex) {
	showSlides(allClusterIndexes[oneClusterIndex] += increaseAmount, oneClusterIndex);
}

function showSlides(newContentIndex, oneClusterIndex) {
	let cluster = document.getElementsByClassName("content" + oneClusterIndex);
	let dots = document.getElementsByClassName("dots" + oneClusterIndex);
	if (newContentIndex < 1) {allClusterIndexes[oneClusterIndex] = 1} //stops the carousel from going back at the beginning of the cluster
	if (cluster.length <= 4) {  //stops the carousel from incrementing for shorter clusters
		allClusterIndexes[oneClusterIndex] = 1;
		newContentIndex = 1;
	} 
	if (newContentIndex + 3 > cluster.length && cluster.length >= 4) { //stops carousel at the end of the cluster
		allClusterIndexes[oneClusterIndex] = 1;
		newContentIndex = 1;
	} 
	for (let i = 0; i < cluster.length; i++) {
		cluster[i].style.display = "none";
		dots[i].className = dots[i].className.replace(" active", "");
	}
	for (let i = 1; i <= 4; i++) {
		if(newContentIndex <= cluster.length) {
			cluster[allClusterIndexes[oneClusterIndex]-2+i].style.display = "inline-block";
			dots[allClusterIndexes[oneClusterIndex]-2+i].className += " active";
			newContentIndex++;
		}
	}
}


//---MODAL FUNCTIONS---//


function openModal(content) {
	var modal = document.getElementsByClassName("modal")[0];
	var modalImg = document.getElementsByClassName("modalContent")[0];
	var span = document.getElementsByClassName("close")[0];
	modal.style.display = "block";
	modalImg.src = content.src;
}

function closeModal() {
	var modal = document.getElementsByClassName("modal")[0];
	modal.style.display = "none";
}


