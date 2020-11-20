
export function getNumbers (id) {
	const url = "https://coronavirusapi-france.now.sh/FranceLiveGlobalData"
	return fetch(url,{
		method: 'GET',
	})
		.then((response) => response.json())
		.catch((error) => console.log(error))
}
