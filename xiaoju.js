const appName = 'xiaojuapp';
window[appName] = {
  getLocation: customeGetLocation,
  launchNav: customeLaunchNav
};

function h5Location(cb) {
  navigator.geolocation.getCurrentPosition(
    pos => {
      const { latitude, longitude } = pos.coords;
      cb({ lat: latitude, lng: longitude });
    },
    error => {
      // 瀹归敊 榛樿鍖椾含
      cb({ error, lat: 39.92, lng: 116.46 });
    },
    {
      enableHighAccuracy: true
    }
  );
}

function openGMap(params) {
  const { fromLng, fromLat, toLng, toLat, toName } = params;
  // 鑾峰彇鍒扮敤鎴峰畾浣嶄俊鎭紝璧板鑸ā寮忥紱鏈幏鍙栧埌鐢ㄦ埛瀹氫綅淇℃伅锛岃蛋鍗曠偣鏍囨敞妯″紡
  const url = `https://uri.amap.com/navigation?from=${fromLng},${fromLat}&to=${toLng},${toLat},${toName}&mode=car&policy=1&callnative=1`;
  window.location.href = url;
  console.log(url)
}

function customeGetLocation(params, resCall, errCall) {
  // H5 瀹氫綅
  if (navigator.geolocation) {
    h5Location(resCall);
  } else {
    // console.log()
    errCall && errCall(new Error('娴忚鍣ㄤ笉鏀寔瀹氫綅'));
  }
}

function customeLaunchNav(params) {
  openGMap(params);
}
